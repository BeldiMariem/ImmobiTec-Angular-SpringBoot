package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Availability;
import immobi.tec.immobitec.entities.DormRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DormRoomRepository extends JpaRepository<DormRoom,Integer> {
    @Query("SELECT COUNT(d) FROM DormRoom d WHERE d.status <> :availability")
    long countByAvailabilityNot(@Param("availability") Availability availability);

    @Query("SELECT COUNT(d) FROM DormRoom d WHERE d.status = :availability")
    long countByAvailability(@Param("availability") Availability availability);


}
