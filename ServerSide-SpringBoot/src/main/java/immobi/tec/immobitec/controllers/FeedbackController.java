package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Feedback;
import immobi.tec.immobitec.services.IFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/feedBack")
public class FeedbackController {
IFeedbackService iFeedbackService;
    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return iFeedbackService.retrieveAllFeedbacks();
    }

    @GetMapping("/feedback/{id}")
    public Feedback getFeedbackById(@PathVariable("id") Integer id) {
        return iFeedbackService.retrieveFeedback(id);
    }






    @GetMapping("/feedbacks/dormRoom/{dormRoomId}")
    public List<Feedback> getFeedbacksByDormRoom(@PathVariable("dormRoomId") Integer dormRoomId) {
        return iFeedbackService.retrieveFeedbacksByDormRoom(dormRoomId);
    }






    @GetMapping("/feedbacks/appUser/{appUserId}")
    public List<Feedback> getFeedbacksByAppUser(@PathVariable("appUserId") Integer appUserId) {
        return iFeedbackService.retrieveFeedbacksByAppUser(appUserId);
    }

    @PostMapping("/feedback/{userId}/{roomId}")
    public ResponseEntity<String> addFeedback(@RequestBody Feedback fb, @PathVariable("userId") int userId, @PathVariable("roomId") int roomId) {
        return iFeedbackService.addFeedback(fb, userId, roomId);
    }

    @PutMapping("/updatefeedback")
    @ResponseBody
    public Feedback updateFeedback(@RequestBody Feedback fb){
        return  iFeedbackService.updateFeedback(fb);
    }


    @Transactional
    @DeleteMapping("/feedback/{id}")
    public void deleteFeedback(@PathVariable("id") int id) {
        iFeedbackService.deleteFeedback(id);
    }

    @PutMapping("/feedback/boost/{id}")
    public Feedback addBoost(@PathVariable("id") Integer id) {
        return iFeedbackService.addBoost(id);
    }

}
