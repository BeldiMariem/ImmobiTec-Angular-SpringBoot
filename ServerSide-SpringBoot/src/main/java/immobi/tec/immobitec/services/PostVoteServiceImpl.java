package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.*;
import immobi.tec.immobitec.repositories.PostRepository;
import immobi.tec.immobitec.repositories.PostVoteRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PostVoteServiceImpl implements IPostVoteService {
    PostVoteRepository postVoteRepository;
    PostRepository postRepository;
    UserRepository userRepository;



    @Override
    public void vote(int idPost, TypeVote typeVote, int idUser) {
            Post post=postRepository.findById(idPost).orElse(null);


            AppUser user=userRepository.findById(idUser).orElse(null);

            Optional<PostVote> optionalVote = postVoteRepository.findByPostAndUser(post, user);

            if (optionalVote.isPresent()) {
                PostVote vote = optionalVote.get();

                if (vote.getVoteType().equals(typeVote)) {
                    // user is trying to vote again with the same vote type, return error
                    System.out.println("You have already " + typeVote.toString().toLowerCase() + "d this post.");
                } else {
                    // user is changing his vote
                    vote.setVoteType(typeVote);
                    postVoteRepository.save(vote);

                    if (typeVote.equals(TypeVote.UPVOTE)) {
                        post.setLikes(post.getLikes() + 1);
                        post.setDislikes(post.getDislikes() - 1);
                    } else {
                        post.setLikes(post.getLikes() - 1);
                        post.setDislikes(post.getDislikes() + 1);
                    }

                    postRepository.save(post);

                }
            } else {
                // create new vote
                PostVote vote = new PostVote();
                vote.setPost(post);
                vote.setUser(user);
                vote.setVoteType(typeVote);

                postVoteRepository.save(vote);

                if (typeVote.equals(TypeVote.UPVOTE)) {
                    post.setLikes(post.getLikes() + 1);
                } else {
                    post.setDislikes(post.getDislikes() + 1);
                }

                postRepository.save(post);

            }

        }
    }
