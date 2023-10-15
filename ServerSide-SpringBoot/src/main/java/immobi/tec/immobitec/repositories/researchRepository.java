package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.researches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface researchRepository extends JpaRepository<researches,Integer> {


    @Query("SELECT r FROM researches r, AppUser a where r.user.id_user=a.id_user and a.id_user=:id_user")
    List<researches> retrieveresearchesByUser(@Param("id_user") int id_user);

}
