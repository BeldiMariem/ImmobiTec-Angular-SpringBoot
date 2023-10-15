package immobi.tec.immobitec.controllers;

import com.itextpdf.text.BaseColor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import immobi.tec.immobitec.entities.Appointement;
import immobi.tec.immobitec.repositories.AppointmentRepositories;
import immobi.tec.immobitec.services.IAppointement;
import immobi.tec.immobitec.services.ServiceAppointement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/Appointment")

public class AppointementController {

    IAppointement iAppointement;
    ServiceAppointement serviceAppointement;
AppointmentRepositories appointmentRepositories;
    @PostMapping("/addAppointement/{idUser}/{idService}")
    @ResponseBody
    public Appointement addAppointement(@RequestBody Appointement a,@PathVariable("idUser") int idUser,@PathVariable("idService")int idService){
        return iAppointement.addAndAssignAppointementtoUserAndService(a,idUser,idService);
    }
   /* @DeleteMapping("/deleteAppointement/{currentDate}")
    @ResponseBody
    public void deleteAppointement(@PathVariable("currentDate") LocalDate currentDate){iAppointement.deleteAppointement(currentDate);}
*/
   @DeleteMapping("/deleteAppointement/expired")
   public ResponseEntity<String> deleteExpiredAppointments() {
       try {
           serviceAppointement.deleteAppointement(LocalDate.now());
           return ResponseEntity.ok("Expired appointments deleted successfully.");
       } catch (Exception e) {
           return ResponseEntity.badRequest().body("Error deleting appointments: " + e.getMessage());
       }
   }
   @DeleteMapping("/deleteAppointementById/{id}")
   public void deleteAppointementById(@PathVariable("id")int id){
       iAppointement.deleteAppointementById(id);
   }


@PutMapping("/updateAppointement")
    @ResponseBody
    public Appointement updateAppointement(@RequestBody Appointement appointement){
        return iAppointement.updateAppointement(appointement);
    }
    @GetMapping("/getAllAppointement")
    @ResponseBody
    public List<Appointement> getAllAppointement(){return iAppointement.getAllAppointement();}

   @GetMapping("/getAppointementByID/{id}")
   @ResponseBody
    public Appointement getAppointementByID(@PathVariable("id") int id ){
       return  iAppointement.getAppointementByID(id);
    }



    @GetMapping("/appointements/pdf")
    public ResponseEntity<byte[]> downloadappointementsPdf() throws IOException, DocumentException {
        List<Appointement> appointmentsList = appointmentRepositories.findAll(); // récupération des données de réclamations depuis la base de données à l'aide de JPA

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // création d'un flux de sortie pour stocker le PDF généré
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        PdfPTable table = new PdfPTable(2); // création d'une table avec 2 colonnes

// ajout des en-têtes de colonne à la table
        Stream.of("ID", "Date")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(Color.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

// ajout des données d'appointments à la table
        for (Appointement appointment : appointmentsList) {
            table.addCell(String.valueOf(appointment.getId_appointement()));
            table.addCell(appointment.getDate_appointement().toString()); // remplacez "getDateAppointment()" par la méthode appropriée pour récupérer la date de l'appointment
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "appointments.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);


    }
}
