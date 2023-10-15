package immobi.tec.immobitec.controllers;



import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import com.lowagie.text.Document;
import immobi.tec.immobitec.entities.Auction;
import immobi.tec.immobitec.entities.Mise;
import immobi.tec.immobitec.repositories.AuctionRepository;
import immobi.tec.immobitec.services.IAuctionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@RestController
@AllArgsConstructor
@RequestMapping("/Auction")


@CrossOrigin(origins = "http://localhost:4200")


public class AuctionController {

    IAuctionService iAuction;
    AuctionRepository auctionRepository;


    @PostMapping("/{UserId}/{idProperty}/auction")
    public ResponseEntity<Auction>  addActiontoUserandProp(@PathVariable("UserId") int UserId,@PathVariable("idProperty") int idProperty, @RequestBody Auction auction) {
        Auction savedauction = iAuction.addActiontoUserandProp( UserId,idProperty,auction);
        return new ResponseEntity<>(savedauction, HttpStatus.CREATED);
    }

    @GetMapping("/getAllAuction")
    public List<Auction> getAllAuction(){
        return iAuction.getAllAuctions();
    }


    @GetMapping("/orderByTotalMisesMontant")
    public List<Auction> getAuctionsOrderByTotalMisesMontant() {
        return iAuction.findAllOrderByTotalMisesMontant();
    }


    @GetMapping("/getAuctionByid/{id_auction}")
    public Auction getAuctionByid(@PathVariable("id_auction") int id_auction)
    {
        return iAuction.getAuctionByid(id_auction);
    }


    @DeleteMapping("/deleteAuction/{id_auction}")
    public void deleteAuction(@PathVariable int id_auction){
        iAuction.deleteAuction(id_auction);
    }



    @PutMapping("/updateAuction")
    public Auction updateAuction(@RequestBody Auction auction)
    {
        iAuction.updateAuction(auction);
        return auction;
    }


    @PostMapping("/{auctionId}/{idUser}/mise")
    public ResponseEntity<Mise> addmiseToAuction(@PathVariable("auctionId") int idAuction,@PathVariable("idUser") int idUser, @RequestBody Mise mise) {
        Mise savedMise = iAuction.addMiseToAuction( idAuction,idUser,mise);
        return new ResponseEntity<>(savedMise, HttpStatus.CREATED);
    }

    @GetMapping("/getAllMise")
    public List<Mise> getAllMIse(){
        return iAuction.getAllMise();
    }


    @GetMapping("/highestBid")
    public List<Auction> findAuctionWithHighestBid() {
        List<Auction> auction = iAuction.findAuctionWithHighestBid();
       return auction;
    }

    @GetMapping("/current")
    public List<Auction> getCurrentAuctions() {
        List<Auction> auction = iAuction.getCurrentAuctions();
        return auction;
    }


    @GetMapping("/userPercentage")
    public ResponseEntity<Map<Integer, Double>> getUserPercentageByAuction() {
        Map<Integer, Double> auctionUserPercentageMap = iAuction.getUserPercentageByAuction();
        return ResponseEntity.ok(auctionUserPercentageMap);
    }


    @GetMapping("/winners/export")
    public ResponseEntity<String> exportWinnersToExcel(@RequestParam String fileName) throws IOException {
        iAuction.exportWinnersToExcel(fileName);
        return ResponseEntity.ok("Winners exported to Excel file: " + fileName);
    }

    @GetMapping("/auctions/pdf")
    public ResponseEntity<byte[]> downloadAuctionsPdf() throws IOException, DocumentException {
        List<Auction> auctionsList = auctionRepository.findAll(); // récupération des données des enchères depuis la base de données à l'aide de JPA

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); // création d'un flux de sortie pour stocker le PDF généré
        Document document = new Document();
        PdfWriter.getInstance(document, baos);

        document.open();
        PdfPTable table = new PdfPTable(6); // création d'une table avec 6 colonnes

        // ajout des en-têtes de colonne à la table
        Stream.of("ID", "Description", "Frais d'inscription", "Date de début", "Date de fin", "Vainqueur")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(Color.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

        // ajout des données des enchères à la table
        for (Auction auction : auctionsList) {
            table.addCell(String.valueOf(auction.getId_auction()));
            table.addCell(auction.getDescription());
            table.addCell(String.valueOf(auction.getEntryFee()));
            table.addCell(auction.getStartDate().toString());
            table.addCell(auction.getEndDate().toString());
            table.addCell(auction.getWinner());
        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "auctions.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
}






