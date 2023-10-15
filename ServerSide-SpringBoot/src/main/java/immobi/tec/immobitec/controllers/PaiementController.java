package immobi.tec.immobitec.controllers;



import com.stripe.Stripe;
import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;

import com.stripe.model.Charge;
import immobi.tec.immobitec.entities.Announcement;
import immobi.tec.immobitec.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Paiment")
public class PaiementController {




   /* @PostMapping("/create-payment-intent/{Montant}")
    public CreatePaymentResponse CreatePaiementIntent(@PathVariable Long Montant) throws StripeException {

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                            .setAmount(Montant * 100L)
                            .setCustomer("hazembayoudh886@gmail.com")
                            .setCurrency("usd")
                            .build();
                   PaymentIntent  paymentIntent = PaymentIntent.create(params);

            return new CreatePaymentResponse(paymentIntent.getClientSecret());
    }*/

    private StripeService stripeService;
    @Autowired
    AnnouncementRepository announcementRepository;

    public PaiementController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/Paiement/{idAnnouncements}")
    public String charge(@RequestParam String currency ,@PathVariable int idAnnouncements) throws StripeException {
        return stripeService.charge(idAnnouncements, currency);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/charge/{idAnnouncements}")
    public ResponseEntity<String> chargeCard(@RequestBody String token,@PathVariable int idAnnouncements) throws StripeException {
        Announcement announcement = announcementRepository.findById(idAnnouncements).orElse(null);

        Stripe.apiKey = "sk_test_51Ks89UCXTqtJcSxPSlNzU1YoSmb3jNW4ja2I6xw9nH4vVzQ3u4ACnJQ8sUr5jQODs5ce9OH8Ys7VFnoSkjRv5xDB00frb9D2f3";

        Map<String, Object> params = new HashMap<>();
        params.put("amount", 20 * 100); // amount in cents, adjust as needed
        params.put("currency", "usd");
        params.put("description", "Charge for service");
        params.put("source", token);

        try {
            Charge charge = Charge.create(params);
            if(charge.getStatus().equals("succeeded")){
                announcement.setPaiement(true);
                announcement.setBoosted(true);
                announcementRepository.save(announcement);
            }
            return ResponseEntity.ok("Payment successful");
        } catch (CardException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
