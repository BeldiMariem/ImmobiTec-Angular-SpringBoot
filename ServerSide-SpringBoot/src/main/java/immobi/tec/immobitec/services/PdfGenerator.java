package immobi.tec.immobitec.services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import immobi.tec.immobitec.entities.Appointement;
import immobi.tec.immobitec.entities.Service;
import immobi.tec.immobitec.repositories.ServiceRepositories;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class PdfGenerator {
    ServiceRepositories serviceRepositories;
    public void export(HttpServletResponse response,int id) throws IOException {
        Service service = serviceRepositories.findById(id).orElse(null);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Appointements List", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);


        document.add(paragraph);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Date Appointement",FontFactory.getFont("Comic Sans MS", 12, Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Service Name",FontFactory.getFont("Comic Sans MS", 12,Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.BLACK);
        table.addCell(cell);


        for(Appointement a: service.getAppointements()){

            cell = new PdfPCell(new Phrase(String.valueOf(a.getDate_appointement()),FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.WHITE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(service.getTitle()),FontFactory.getFont("Comic Sans MS", 12)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(Color.WHITE);
            table.addCell(cell);
        }
        document.add(table);

        document.close();
    }
}
