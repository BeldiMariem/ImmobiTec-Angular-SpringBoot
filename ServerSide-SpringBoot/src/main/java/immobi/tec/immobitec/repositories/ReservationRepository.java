package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    @Query("select r from Reservation r")
    List<Reservation> getAll();

    @Modifying
    @Query("delete from Reservation r where r.id_reservation = :id")
    void deleteReservationById(@Param("id") int id);
}


