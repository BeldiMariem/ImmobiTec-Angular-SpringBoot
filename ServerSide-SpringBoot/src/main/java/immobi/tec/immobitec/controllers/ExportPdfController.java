package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.services.PdfGenerator;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class ExportPdfController {

        private final PdfGenerator pdfGeneratorService;




        @GetMapping("/export/{id}")
        public void exportReservations(HttpServletResponse response,@PathVariable int id) throws IOException {

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"reservations.pdf\"");

            pdfGeneratorService.export(response,id);
        }
}
