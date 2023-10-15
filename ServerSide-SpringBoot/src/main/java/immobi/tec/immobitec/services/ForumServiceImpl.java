package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Forum;
import immobi.tec.immobitec.entities.Post;
import immobi.tec.immobitec.repositories.ForumRepository;
import immobi.tec.immobitec.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ForumServiceImpl implements IForumService {
    ForumRepository forumRepository;
    PostRepository postRepository;

    @Override
    public Forum getForumById(int id) {
        return forumRepository.findById(id).orElse(null);
    }

    @Override
    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    @Override
    public Forum addForum(Forum f) {
        return forumRepository.save(f);
    }

    @Override
    public Forum updateForum(Forum f) {
        return forumRepository.save(f);
    }

    @Override
    public void deleteForum(int id) {
        forumRepository.deleteById(id);
    }

    @Override
    public List<Post> getAllPostsByForum(int id) {
        Forum f=forumRepository.findById(id).orElse(null);
        Set<Post> posts=f.getPosts();
        List<Post> listPosts=new ArrayList<>();
        for (Post p : posts){

                listPosts.add(p);

        }
        return listPosts;
    }

    @Override
    public List<Post> getBoostedPost(int id) {
        Forum f=forumRepository.findById(id).orElse(null);
        List<Post> Boostedposts=new ArrayList<>();
        System.out.println("FORUUUUUM "+f.getTitle());
        Set<Post> posts=f.getPosts();
        for (Post p : posts){
            if (p.getBoosted()){
                Boostedposts.add(p);
            }
        }

        return Boostedposts;
    }


}
