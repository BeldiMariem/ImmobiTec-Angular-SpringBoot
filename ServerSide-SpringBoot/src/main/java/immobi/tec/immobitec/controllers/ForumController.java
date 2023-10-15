package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Forum;
import immobi.tec.immobitec.entities.Post;
import immobi.tec.immobitec.filter.ProfanityFilter;
import immobi.tec.immobitec.services.IForumService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/forum")
public class ForumController {
    IForumService iForumService;
    ProfanityFilter profanityFilter;

    @GetMapping("/AllForums")
        public List<Forum> allForums(){

        return iForumService.getAllForums();
        }

    @GetMapping("/getAllPostsByForum/{id}")
        public List<Post> getAllPostsByForum(@PathVariable("id")int id){
        return iForumService.getAllPostsByForum(id);
    }

    @GetMapping("/ForumById/{id}")
    public Forum getForumById(@PathVariable("id") int id){
        return iForumService.getForumById(id);
    }

    @PostMapping("/AddForum")
    public Forum addForum(@RequestBody Forum f){





        f.setDescription(profanityFilter.filterMessage(f.getDescription()));



        return iForumService.addForum(f);
    }
    @PutMapping("/updateForum")
    public Forum updateForum(@RequestBody Forum f){
        return iForumService.updateForum(f);
    }

    @DeleteMapping("/deleteForum/{id}")
    public void deleteForum(@PathVariable("id") int id){
        iForumService.deleteForum(id);
    }

    @GetMapping("/boostedPosts/{id}")
    public List<Post>getBoostedPost(@PathVariable("id") int id){
        return iForumService.getBoostedPost(id);
    }
}
