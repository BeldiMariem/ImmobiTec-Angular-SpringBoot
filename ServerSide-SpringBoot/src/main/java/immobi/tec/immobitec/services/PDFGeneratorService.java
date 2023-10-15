package immobi.tec.immobitec.services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import immobi.tec.immobitec.entities.Reservation;
import immobi.tec.immobitec.repositories.ReservationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class PDFGeneratorService {
     ReservationRepository reservationRepository;
   public void export(HttpServletResponse response) throws IOException {
        List<Reservation> reservations = reservationRepository.findAll();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Reservations List", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);


        document.add(paragraph);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Date Reservation",FontFactory.getFont("Comic Sans MS", 12, Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Room",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("User name",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Number Nights",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CheckinDate",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("CheckoutDate",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);
        for(Reservation r: reservations){
             cell = new PdfPCell(new Phrase(String.valueOf(r.getDate_reservation()),FontFactory.getFont("Comic Sans MS", 12)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(Color.WHITE);
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(String.valueOf(r.getDormRoom().getName()),FontFactory.getFont("Comic Sans MS", 12)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(Color.WHITE);
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(String.valueOf(r.getAppUser().getName()+" "+r.getAppUser().getLastname()),FontFactory.getFont("Comic Sans MS", 12)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(Color.WHITE);
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(String.valueOf(r.getNumber_nights()),FontFactory.getFont("Comic Sans MS", 12)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(Color.WHITE);
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(String.valueOf(r.getCheckindate()),FontFactory.getFont("Comic Sans MS", 12)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(Color.WHITE);
             table.addCell(cell);

             cell = new PdfPCell(new Phrase(String.valueOf(r.getCheckoutdate()),FontFactory.getFont("Comic Sans MS", 12)));
             cell.setHorizontalAlignment(Element.ALIGN_CENTER);
             cell.setBackgroundColor(Color.WHITE);
             table.addCell(cell);
        }
        document.add(table);

        document.close();
    }
}
