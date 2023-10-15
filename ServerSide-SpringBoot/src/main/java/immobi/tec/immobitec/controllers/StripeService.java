package immobi.tec.immobitec.controllers;

import com.stripe.Stripe;

import com.stripe.exception.CardException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import immobi.tec.immobitec.entities.Announcement;
import immobi.tec.immobitec.repositories.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Autowired
    AnnouncementRepository announcementRepository;

    public StripeService(@Value("${stripe.secretKey}") String apiKey) {
        Stripe.apiKey = apiKey;
    }

    public String  charge(int idAnnouncements, String currency) throws StripeException {
        Announcement announcement = announcementRepository.findById(idAnnouncements).orElse(null);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 20 * 100L);
        params.put("currency", currency);
        params.put("source", "tok_visa");
         Charge charge = Charge.create(params);
        if(charge.getStatus().equals("succeeded")){
            announcement.setPaiement(true);
            announcement.setBoosted(true);
            announcementRepository.save(announcement);
            return "Paiement Succed !!";
        }
        return null;

    }

}
