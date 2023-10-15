package immobi.tec.immobitec.services;


import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Auction;
import immobi.tec.immobitec.entities.Mise;
import immobi.tec.immobitec.entities.Property;
import immobi.tec.immobitec.repositories.AuctionRepository;
import immobi.tec.immobitec.repositories.MiseRepository;
import immobi.tec.immobitec.repositories.PropertyRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class AuctionServiceImp implements IAuctionService {
    AuctionRepository auctionRepo;
    MiseRepository miseRepo;
    UserRepository userRepo;
    PropertyRepository propertyRepo ;

    @Override
    public Auction addActiontoUserandProp(int UserId ,int propretId ,Auction a) {
      /*  return auctionRepo.save(a);*/

        Property proprety = propertyRepo.findById(propretId).orElse(null);
        AppUser appUser = userRepo.findById(UserId).orElse(null);
a.setProperty(proprety);
a.setUser(appUser);
        return auctionRepo.save(a);


    }

    @Override
    public List<Auction> getAllAuctions() {
        return auctionRepo.findAll();
    }

    @Override
    public Auction getAuctionByid(int id_auction) {
        return auctionRepo.findById(id_auction).orElse(null);
    }

    @Override
    public void deleteAuction(int id_auction) {
        auctionRepo.deleteById(id_auction);
    }

    @Override
    public Auction updateAuction(Auction a) {
        return auctionRepo.save(a);
    }

    @Override
    public List<Auction> findAuctionWithHighestBid() {
        List<Auction> a = auctionRepo.findAuctionWithHighestBid();
        return a;
    }


    @Override
    public List<Auction> getCurrentAuctions() {
        List<Auction> b = auctionRepo.findAuctionByDate();
        return b;
    }


    @Override
    public Mise addMiseToAuction(int idAuction, int idUser, Mise mise) {
        Auction auction = auctionRepo.findById(idAuction).orElse(null);
        AppUser appUser = userRepo.findById(idUser).orElse(null);

        if (auction != null && appUser != null) {
            Set<Mise> mises = auction.getMises();
            if ((auction.getEntryFee() == 0 || mise.getMontant() >= auction.getEntryFee()) && (mises.isEmpty() || mise.getMontant() > Collections.max(mises, Comparator.comparing(Mise::getMontant)).getMontant()))
            {
                if (mise.getMontant() <= appUser.getWallet()) {
                    appUser.setWallet(appUser.getWallet() - mise.getMontant());
                    userRepo.save(appUser);

                    // Si un gagnant existe déjà, ajoutez la mise maximale à son portefeuille
                    if (auction.getWinner() != null) {
                        Mise oldWinnerMise = Collections.max(mises, Comparator.comparing(Mise::getMontant));
                        AppUser oldWinner = oldWinnerMise.getUser();
                        oldWinner.setWallet(oldWinner.getWallet() + oldWinnerMise.getMontant());
                        userRepo.save(oldWinner);
                    }

                } else {
                    return null;
                }
                mise.setAuction(auction);
                mise.setUser(appUser);
                Mise savedMise = miseRepo.save(mise);
                auction.getMises().add(savedMise);
                updateWinnerUserName(idAuction);
                return savedMise;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }


    @Override
    public List<Mise> getAllMise() {
        return miseRepo.findAll();
    }


    public void updateWinnerUserName(int auctionId) {
        Auction auction = auctionRepo.findById(auctionId).orElse(null);

        if (auction != null) {
            Set<Mise> mises = auction.getMises();

            if (!mises.isEmpty()) {
                Mise maxMise = Collections.max(mises, Comparator.comparing(Mise::getMontant));

                if (maxMise.getUser() != null) {
                    auction.setWinner(maxMise.getUser().getName() /*+ " " + maxMise.getUser().getLastname()*/);
                    auctionRepo.save(auction);
                }
            }
        }
    }


    public List<Auction> findAllOrderByTotalMisesMontant() {
        return auctionRepo.findAllOrderByTotalMisesMontant();
    }


    public Map<Integer, Double> getUserPercentageByAuction() {

        Map<Integer, Double> auctionUserPercentageMap = new HashMap<>();

        List<Auction> auctions = auctionRepo.findAll();
        Long totalUsers = userRepo.count();

        for (Auction auction : auctions) {
            Set<Mise> mises = auction.getMises();
            int uniqueUsers = 0;

            if (!mises.isEmpty()) {
                Set<AppUser> users = new HashSet<>();

                for (Mise mise : mises) {
                    users.add(mise.getUser());
                }

                uniqueUsers = users.size();
            }

            double userPercentage = ((double) uniqueUsers / totalUsers) * 100;

            auctionUserPercentageMap.put(auction.getId_auction(), userPercentage);
        }

        return auctionUserPercentageMap;
    }

    /*Exel */

        @Override
        public void exportWinnersToExcel(String fileName) throws IOException {
            List<Auction> auctions = auctionRepo.findByWinnerIsNotNull();

            
            // Créer un nouveau fichier Excel
            Workbook workbook = new XSSFWorkbook();

            // Créer une nouvelle feuille dans le fichier Excel
            Sheet sheet = workbook.createSheet("Winners");

            // Créer une ligne d'entête pour le fichier Excel
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Auction ID");
            header.createCell(1).setCellValue("Winner");

            // Écrire les données des gagnants dans le fichier Excel
            int rowNum = 1;
            for (Auction auction : auctions) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(auction.getId_auction());
                row.createCell(1).setCellValue(auction.getWinner());
            }

            // Écrire le fichier Excel sur le burau
            String desktopPath = System.getProperty("user.home") + "/Desktop/";
            String filePath = desktopPath + fileName;
            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        }




        private JavaMailSender javaMailSender;



    //@Scheduled(fixedRate = 180000000) // run once per day at midnight

    //  @Scheduled(fixedRate = 1000) // run once per day at midnight



    @Scheduled(fixedRate = 18000) // run once per day at midnight


    public void checkAuctions() throws MessagingException {
        System.out.println("khdem");


        List<Auction> auctions = auctionRepo.findAll();
        Date now = new Date();

        for (Auction auction : auctions) {
            if (now.after(auction.getEndDate())) {
                AppUser user = userRepo.findByName(auction.getWinner());

                  MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper =new MimeMessageHelper(message);
                helper.setFrom("immobitec0@gmail.com");
                helper.setTo(user.getEmail());
                    helper.setSubject("Winner");
                    helper.setText("You are the winner ");
                    javaMailSender.send(message);

            }
        }
    }
    }


