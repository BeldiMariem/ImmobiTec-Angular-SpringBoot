package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.DormRoom;
import immobi.tec.immobitec.entities.Feedback;
import immobi.tec.immobitec.entities.Reservation;
import immobi.tec.immobitec.repositories.DormRoomRepository;
import immobi.tec.immobitec.repositories.FeedbackRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements IFeedbackService {
    FeedbackRepository feedbackRepository;

    UserRepository userRepository;

    DormRoomRepository dormRoomRepository;


    @Override
    public ResponseEntity<String> addFeedback(Feedback fb, int id_User, int id_Room) {
        AppUser au = userRepository.findById(id_User).orElse(null);
        DormRoom d = dormRoomRepository.findById(id_Room).orElse(null);
        if (fb == null || fb.getFeedback_message() == null || fb.getFeedback_message().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Feedback cannot be empty.");
        }


        fb.setAppUser(au);
        fb.setDormRoom(d);
        fb.setFeedback_message(fb.getFeedback_message());
        feedbackRepository.save(fb);

        return ResponseEntity.ok("Feedback added successfully");
    }

    @Override
    public Feedback updateFeedback(Feedback fb) {
        return feedbackRepository.save(fb);
    }


    @Override
    public void deleteFeedback(int id_feedback) {
        Feedback feedback = feedbackRepository.findById(id_feedback).orElse(null);


        // Delete the reservation record
        feedbackRepository.deleteFeedbackById(id_feedback);
       // feedbackRepository.deleteById(id_feedback);
    }




    @Override
    public List<Feedback> retrieveAllFeedbacks() {
        return feedbackRepository.findAll();
    }


    @Override
    public Feedback retrieveFeedback(Integer id) {
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        return feedback .orElse(null);
    }


    @Override
    public List<Feedback> retrieveFeedbacksByDormRoom(Integer dormRoomId) {
        DormRoom dormRoom = dormRoomRepository.findById(dormRoomId).orElse(null);
        if (dormRoom == null) {
            return null;
        }
        return feedbackRepository.findByDormRoom(dormRoom);
    }

    @Override
    public List<Feedback> retrieveFeedbacksByAppUser(Integer appUserId) {
        AppUser appUser = userRepository.findById(appUserId).orElse(null);
        if (appUser == null) {
            return null;
        }
        return feedbackRepository.findByAppUser(appUser);
    }

    @Override
    public Feedback addBoost(Integer id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback == null) {
            return null;
        }
        int currentBoosts = feedback.getBoosts() != null ? feedback.getBoosts() : 0;
        feedback.setBoosts(currentBoosts + 1);
        feedback.setBoosted(true);
        feedbackRepository.save(feedback);
        return feedback;
    }


}


