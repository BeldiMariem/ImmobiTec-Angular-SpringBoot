package immobi.tec.immobitec.services;


import immobi.tec.immobitec.entities.Auction;
import immobi.tec.immobitec.entities.Mise;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IAuctionService {
    public Auction addActiontoUserandProp(int UserId ,int propretId ,Auction a) ;
    public List<Auction> getAllAuctions();
    public Auction getAuctionByid(int id_auction);
    public void deleteAuction(int id_auction);
    public Auction updateAuction(Auction a);
   public List<Auction>  findAllOrderByTotalMisesMontant();

    public List<Auction> findAuctionWithHighestBid();

    public void updateWinnerUserName(int auctionId);
    public List<Auction> getCurrentAuctions();

    public Mise addMiseToAuction(int idAuction,int idUser , Mise mise);
    public List<Mise> getAllMise();

    public Map<Integer, Double> getUserPercentageByAuction();



    void exportWinnersToExcel(String fileName) throws IOException;
}
