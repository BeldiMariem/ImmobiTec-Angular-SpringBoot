package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Forum;
import immobi.tec.immobitec.entities.TypeVote;
import immobi.tec.immobitec.services.IPostVoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/postVote")
public class PostVoteController {
    IPostVoteService iPostVoteService;

//comment
    @GetMapping("/like/{postId}/{idUser}")
    public ResponseEntity<String> likePost(@PathVariable("postId") int postId,@PathVariable("idUser")int idUser) {
        iPostVoteService.vote(postId, TypeVote.UPVOTE,idUser);
        //comment
        return new ResponseEntity<>( HttpStatus.OK);
    }

    //response
    @GetMapping("/dislike/{postId}/{idUser}")
    public ResponseEntity<String> dislikePost(@PathVariable("postId") int postId,@PathVariable("idUser")int idUser) {
        iPostVoteService.vote(postId, TypeVote.DOWNVOTE,idUser);
        return new ResponseEntity<>( HttpStatus.OK);


    }
}
