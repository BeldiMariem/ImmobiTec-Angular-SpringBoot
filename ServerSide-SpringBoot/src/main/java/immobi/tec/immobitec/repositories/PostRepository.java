package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Forum;
import immobi.tec.immobitec.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findPostByUser(AppUser user);
    List<Post> findAllByForum(Forum forum);

    List<Post> findByBoostedIsTrue();
  //  List<Post> findAllByForumAndBoosted(Forum forum);
}
