package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.DormRoom;
import immobi.tec.immobitec.entities.Reservation;
import immobi.tec.immobitec.repositories.ReservationRepository;
import immobi.tec.immobitec.services.IReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/reservation")
public class ReservationController {
    IReservationService iReservationService;
    private final ReservationRepository reservationRepository;

    @PostMapping("/addReservation/{idUser}/{idRoom}")
    @ResponseBody
    public Reservation addReservation(@RequestBody Reservation r,@PathVariable("idUser") int idUser,@PathVariable("idRoom") int idRoom){
        return iReservationService.addReservation(r,idUser,idRoom);
    }


    @GetMapping("/getAllReservations")
    public List<Reservation> getAllReservations(){
        return iReservationService.getAllReservations();
    }

    @GetMapping("/getReservationByID/{id}")
    public Reservation getReservationByID(@PathVariable("id") int id){
        return iReservationService.getReservationByID(id);
    }

    @PutMapping("/updateReservation")
    @ResponseBody
    public Reservation updateReservation(@RequestBody Reservation r){
        return iReservationService.updateReservation(r);
    }

    @DeleteMapping("/deleteReservation/{id}")
    public void deleteReservation(@PathVariable("id") int id){
        iReservationService.deleteReservation(id);
    }



    @GetMapping("/mostReservedMonth")
    public String mostReservedMonth() {
        String mostReservedMonth = iReservationService.getMostReservedMonth();
        return "The most reserved month is: " + mostReservedMonth;
    }


    @GetMapping("/reservations/revenue")
    public ResponseEntity<Integer> calculateTotalRevenue() {
        List<Reservation> reservations = reservationRepository.findAll();
        int totalRevenue = iReservationService.calculateTotalRevenue(reservations);
        return ResponseEntity.ok(totalRevenue);
    }
}


