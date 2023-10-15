package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Forum;
import immobi.tec.immobitec.entities.Post;

import java.util.List;

public interface IForumService {

     Forum getForumById(int id);

     List<Forum> getAllForums();

     Forum addForum(Forum f);

     Forum updateForum(Forum f);

     void deleteForum(int id);

     List<Post> getAllPostsByForum(int id);

     List<Post> getBoostedPost(int id);
}
