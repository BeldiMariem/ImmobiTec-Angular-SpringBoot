package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Appointement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface AppointmentRepositories extends JpaRepository<Appointement,Integer> {
    @Modifying
    @Transactional
    @Query("delete from Appointement a where a.date_appointement < :currentDate")
    void deleteAppointmentsByDateBefore(@Param("currentDate") java.sql.Date currentDate);


}
