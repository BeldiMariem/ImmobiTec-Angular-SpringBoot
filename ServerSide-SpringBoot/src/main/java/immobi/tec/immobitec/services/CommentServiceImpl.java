package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Comment;
import immobi.tec.immobitec.entities.Post;
import immobi.tec.immobitec.repositories.CommentRepository;
import immobi.tec.immobitec.repositories.PostRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements ICommentService{
    CommentRepository commentRepository;
    PostRepository postRepository;

    IUserService userService;
    UserRepository userRepository;


    @Override
    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }


    @Override
    public Comment addCommentToPost(Comment c, int idPost,int idUser) {
        Post post=postRepository.findById(idPost).orElse(null);
        AppUser user = userRepository.findById(idUser).orElse(null);

        userService.updateScore(user, "COMMENT");

        c.setOwner(user);
        c.setPost(post);
        c.setOwn(user.getName());
        return commentRepository.save(c);
    }

    @Override
    public Comment updateComment(Comment c) {
        Comment oldComment=commentRepository.findById(c.getId_comment()).orElse(null);
        oldComment.setMessage(c.getMessage());
        return commentRepository.save(oldComment);
    }

    @Override
    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
