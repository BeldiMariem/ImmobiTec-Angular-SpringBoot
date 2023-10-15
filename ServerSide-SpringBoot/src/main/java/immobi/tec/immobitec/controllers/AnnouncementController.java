package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Announcement;
import immobi.tec.immobitec.entities.Appointement;
import immobi.tec.immobitec.entities.Property;
import immobi.tec.immobitec.entities.researches;
import immobi.tec.immobitec.repositories.AnnouncementRepository;
import immobi.tec.immobitec.repositories.AppointmentRepositories;
import immobi.tec.immobitec.repositories.PropertyRepository;
import immobi.tec.immobitec.services.IAnnouncement;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Announcement")
public class AnnouncementController {

    @Autowired
    IAnnouncement iAnnouncement;
    AppointmentRepositories appointmentRepositories;
    PropertyRepository propertyRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllAnnouncements")
    public List<Announcement> getAllAnnouncements() {
        return iAnnouncement.getAllAnnouncements();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllAnnouncementsTWO")
    public List<Announcement> getAllAnnouncementsTWO() {
        return iAnnouncement.getAllAnnouncementsTWO();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addAnnouncement/{id}")
    @ResponseBody
    public Announcement addAnnouncement(@PathVariable int id, @RequestBody Announcement a) {
        return iAnnouncement.addAnnouncement(id, a);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addResearch/{id_user}")
    @ResponseBody
    public researches addResearch(@PathVariable int id_user, @RequestBody researches r) {
        return iAnnouncement.addResearch(id_user, r);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateAnnouncement")
    @ResponseBody
    public Announcement updateAnnouncement(@RequestBody Announcement a) {
        return iAnnouncement.updateAnnouncement(a);
    }

    @DeleteMapping("/deleteAnnouncement/{id}")
    public void deleteAnnouncement(@PathVariable("id") int id) {
        iAnnouncement.deleteAnnouncement(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAnnouncementById/{id}")
    public Announcement getAnnouncementByID(@PathVariable("id") int id) {
        return iAnnouncement.getAnnouncementById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAAppointmentById/{id}")
    public Appointement getAppointmentByID(@PathVariable("id") int id) {
        return appointmentRepositories.findById(id).orElse(null);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addAppointement/{idUser}")
    @ResponseBody
    public Appointement addAppointement(@PathVariable int idUser, @RequestParam int id, @RequestBody Appointement a) throws ParseException {
        return iAnnouncement.addAppointement(idUser, id, a);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateAppointementProperty")
    @ResponseBody
    public Appointement addAppointement(@RequestBody Appointement a) throws ParseException {
        return iAnnouncement.updateAppointement(a);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAppointementsByProperty/{id}")
    public List<Appointement> getAppointementsByProperty(@PathVariable int id) {
        return iAnnouncement.getAppointementsByProperty(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/DeleteAppointement/{id}")
    @ResponseBody
    public String DeleteAppointement(@PathVariable int id) {

        return iAnnouncement.DeleteAppointement(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/TopAnnouncements")
    public List<Announcement> TopAnnouncements() {
        return iAnnouncement.TopAnnouncements();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/SuggestedAnnouncements/{id_user}")
    public List<Announcement> SuggestedAnnouncements(@PathVariable int id_user) {
        return iAnnouncement.SuggestedAnnouncements(id_user);
    }

    @PutMapping("/ValideAnnouncement/{id_announcement}")
    public Announcement valideAnnouncement(@PathVariable int id_announcement) {
        return iAnnouncement.valideAnnouncement(id_announcement);
    }

    @GetMapping("/getInvalidAnnouncements")
    public List<Announcement> INVALIDAnnouncements() {
        return iAnnouncement.INVALIDAnnouncements();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/Filter")
    public List<Announcement> FilterAnnouncements(@RequestParam String city,@RequestParam String typeAnnouncement,@RequestParam float MaxBudget,@RequestParam float MinBudget) {
        return iAnnouncement.FilterAnnouncements(city, typeAnnouncement, MaxBudget, MinBudget);
    }

    @GetMapping("/SaleAnnouncements")
    public List<Announcement> SaleAnnouncements(){
        return iAnnouncement.SaleAnnouncements();
    }

    @GetMapping("/RentAnnouncements")
    public List<Announcement> RentAnnouncements(){
        return iAnnouncement.RentAnnouncements();
    }
}
