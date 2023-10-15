package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Comment;
import immobi.tec.immobitec.entities.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
