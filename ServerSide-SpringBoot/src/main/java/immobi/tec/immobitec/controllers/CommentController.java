package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Comment;
import immobi.tec.immobitec.filter.ProfanityFilter;
import immobi.tec.immobitec.services.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comment")
public class CommentController {
    ICommentService iCommentService;
    ProfanityFilter profanityFilter;

    @GetMapping("/getCommentById/{id}")
    public Comment getCommentById(@PathVariable("id")int id){
        return iCommentService.getCommentById(id);
    }

    @PostMapping("/addCommentToPost/{id}/{idUser}")
    public Comment addCommentToPost(@RequestBody Comment c,@PathVariable("id")int id,@PathVariable("idUser")int idUser){

        c.setMessage(profanityFilter.filterMessage(c.getMessage()));

        return iCommentService.addCommentToPost(c,id,idUser);
    }

    @PutMapping("/updateComment")
    public Comment updateComment(@RequestBody Comment c){
        return iCommentService.updateComment(c);
    }

    @DeleteMapping("/deleteComment/{id}")
    public void deleteComment(@PathVariable("id")int id){
        iCommentService.deleteComment(id );
    }
}
