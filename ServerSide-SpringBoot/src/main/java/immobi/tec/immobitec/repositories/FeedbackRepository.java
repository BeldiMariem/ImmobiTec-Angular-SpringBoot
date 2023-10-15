package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.DormRoom;
import immobi.tec.immobitec.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Integer> {

    @Modifying
    @Query("delete from Feedback f where f.id_feedback = :id_feedback")
    void deleteFeedbackById(@Param("id_feedback") int id_feedback);

    List<Feedback> findByAppUser(AppUser appUser);


    List<Feedback> findByDormRoom(DormRoom dormRoom);



}
