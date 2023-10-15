package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.*;
import immobi.tec.immobitec.repositories.ForumRepository;
import immobi.tec.immobitec.repositories.PostRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PostServiceImpl implements IPostService{
    PostRepository postRepository;
    ForumRepository forumRepository;
    UserRepository userRepository;
    IUserService userService;

    @Override
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }


    @Override
    public Post addPost(Post p,int idForum,int idUser) {
        Forum f = forumRepository.findById(idForum).orElse(null);
        AppUser user = userRepository.findById(idUser).orElse(null);

        userService.updateScore(user, "POST");
        p.setDate_post(new Date());

        System.out.println("usermail :"+user.getEmail());

        userService.updateScore(user, "POST");
        p.setDate_post(new Date());

        p.setForum(f);
        p.setUser(user);
        p.setOwner(user.getName());
        return postRepository.save(p);
    }

    @Override
    public Post updatePost(Post p,int idForum) {
        Forum f = forumRepository.findById(idForum).orElse(null);
        Post oldPost= postRepository.findById(p.getId_post()).orElse(null);
        //p.setForum(f);
        oldPost.setPicture(p.getPicture());
        oldPost.setDescription(p.getDescription());
        return postRepository.save(oldPost);

    }

    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getPostsByUser(int idUser) {
        AppUser user= userRepository.findById(idUser).orElse(null);
        return postRepository.findPostByUser(user);
    }

    @Override
    public List<Comment> getAllCommentsByPost(int id) {
        Post post=postRepository.findById(id).orElse(null);
        Set<Comment> comments=post.getComments();
        List<Comment> listComments=new ArrayList<>();
        for (Comment c : comments){
            listComments.add(c);
        }
        return listComments;
    }

    @Override
    public void boostAPost(int id) {
        AppUser user = userRepository.findById(Session.getId_user()).orElse(null);

        Optional<Post> optionalPost=postRepository.findById(id);
        if(optionalPost.isPresent()) {
            Post post=optionalPost.get();
            if (post.getBoosted()) {
                System.out.println("This post is already boosted");
            } else {
                user.setWallet(user.getWallet() - 10);
                post.setBoosted(true);
                this.updatePost(post,post.getForum().getId_forum());
            }
        }
    }

    @Override
    public List<Post> getBoostedPost() {
        List<Post> boostedPosts = new ArrayList<>();
        List<Post> posts=postRepository.findAll();

        System.out.println(posts);
        for (Post post : posts) {
            if (post.getBoosted()) {
                boostedPosts.add(post);
            }
        }
        return boostedPosts;
    }


}
