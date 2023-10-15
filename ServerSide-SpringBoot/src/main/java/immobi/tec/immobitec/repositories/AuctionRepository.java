package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction,Integer> {

   @Query("SELECT a FROM Auction a JOIN FETCH a.mises m WHERE m.montant = (SELECT MAX(m2.montant) FROM Mise m2 WHERE m2.auction.id_auction = a.id_auction)")

   List<Auction> findAuctionWithHighestBid();

   @Query("SELECT b FROM Auction b WHERE b.startDate <= current_date AND b.endDate>= CURRENT_DATE ")
List<Auction> findAuctionByDate();


   @Query("SELECT a FROM Auction a LEFT JOIN a.mises m GROUP BY a ORDER BY SUM(m.montant) DESC")
   List<Auction> findAllOrderByTotalMisesMontant();


   @Query("SELECT a.winner " +
           "FROM Auction a " +
           "GROUP BY a.id_auction")
   List<Object[]> findWinners();

   List<Auction> findByWinnerIsNotNull();

}
