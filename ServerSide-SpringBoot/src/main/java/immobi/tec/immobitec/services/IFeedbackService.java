package immobi.tec.immobitec.services;

import com.stripe.model.BalanceTransaction;
import immobi.tec.immobitec.entities.DormRoom;
import immobi.tec.immobitec.entities.Feedback;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFeedbackService {
    public ResponseEntity<String> addFeedback(Feedback fb, int id_User, int id_Room);
    public Feedback updateFeedback(Feedback fb);
    public void deleteFeedback(int id_feedback);

    List<Feedback> retrieveAllFeedbacks();

    Feedback retrieveFeedback(Integer id);

    List<Feedback> retrieveFeedbacksByDormRoom(Integer dormRoomId);

    List<Feedback> retrieveFeedbacksByAppUser(Integer appUserId);

    Feedback addBoost(Integer id);



}
