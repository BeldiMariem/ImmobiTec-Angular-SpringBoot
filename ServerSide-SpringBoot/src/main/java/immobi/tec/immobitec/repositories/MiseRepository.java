package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Mise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface MiseRepository extends JpaRepository<Mise,Integer> {
    @Query("SELECT m FROM Mise m WHERE m.auction = :idAuction ORDER BY m.montant DESC")
    List<Mise> findWinnerMise(@Param("idAuction") int idAuction);


}
