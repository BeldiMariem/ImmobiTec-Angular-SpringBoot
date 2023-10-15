package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Post;
import immobi.tec.immobitec.entities.PostVote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostVoteRepository extends JpaRepository<PostVote,Integer> {

    Optional<PostVote> findByPostAndUser(Post post, AppUser user);
}
