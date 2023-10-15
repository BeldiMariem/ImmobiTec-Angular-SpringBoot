package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Comment;
import immobi.tec.immobitec.entities.Post;

import java.util.List;

public interface IPostService {
     Post getPostById(int id);

     Post addPost(Post p,int idForum,int idUser);

     Post updatePost(Post p,int idForum);

    void deletePost(int id);

    List<Post> getPostsByUser(int idUser);

    List<Comment> getAllCommentsByPost(int id);

    void boostAPost(int p);

    public List<Post> getBoostedPost();

}
