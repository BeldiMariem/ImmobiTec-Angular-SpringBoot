package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Comment;
import immobi.tec.immobitec.entities.Post;
import immobi.tec.immobitec.filter.ProfanityFilter;
import immobi.tec.immobitec.services.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/post")
public class PostController {
    IPostService iPostService;
    ProfanityFilter profanityFilter;

    @GetMapping("/getPostById/{id}")
    public Post getPostById(@PathVariable("id")int id){
        return iPostService.getPostById(id);
    }


    @PostMapping("/addPost/{id}/{idUser}")
    public Post addPost(@RequestBody Post p,@PathVariable("id")int idForum,@PathVariable("idUser")int idUser){
        p.setDate_post(new Date());
        p.setDescription(profanityFilter.filterMessage(p.getDescription()));
        System.out.println(p.getPicture());
        p.setPicture(profanityFilter.filterMessage(p.getPicture()));
        return iPostService.addPost(p,idForum,idUser);
    }
    @PutMapping("/updatePost/{id}")
    public Post updatePost(@RequestBody Post p,@PathVariable("id")int idForum){
        return iPostService.updatePost(p,idForum);
    }
    @DeleteMapping("/deletePost/{id}")
    public void deletePost(@PathVariable("id")int id){
        iPostService.deletePost(id);
    }

    @GetMapping("/getPostsByUser/{id}")
    public List<Post> getPostsByUser(@PathVariable("id")int idUser){
        return iPostService.getPostsByUser(idUser);
    }
    @GetMapping("/getAllCommentsByPost/{id}")
    public List<Comment> getAllCommentsByPost(@PathVariable("id")int id){
        return iPostService.getAllCommentsByPost(id);
    }
    @GetMapping("/boostPost/{id}")
    public ResponseEntity<String> boostAPost(@PathVariable("id")int id){
        iPostService.boostAPost(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getBoostedPost")
    public List<Post> getBoostedPost(){
        //System.out.println(iPostService.getBoostedPost());
    return iPostService.getBoostedPost();

    }
}
