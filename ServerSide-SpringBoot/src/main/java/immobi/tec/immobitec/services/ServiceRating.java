package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Announcement;
import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Property;
import immobi.tec.immobitec.entities.Rating;
import immobi.tec.immobitec.repositories.AnnouncementRepository;
import immobi.tec.immobitec.repositories.RatingRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceRating implements IRating {

RatingRepository ratingRepository;
AnnouncementRepository announcementRepository;

UserRepository userRepository;
    @Override
    public Rating getRatingById(int id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating addRating(int id_user,int id,Rating r) {
        AppUser user = userRepository.findById(id_user).orElse(null);
        List<Rating> ratings = ratingRepository.findAll();
        Announcement announcement = announcementRepository.findById(id).orElse(null);
        r.setUser(user);
        r.setAnnouncement(announcement);
        for (Rating ra : ratings){
            if(r.getUser() == ra.getUser() && r.getAnnouncement()== ra.getAnnouncement()){
                return null;
            }
        }
        return ratingRepository.save(r);
    }

    @Override
    public Rating updateRating(Rating r) {
        return ratingRepository.save(r);
    }

    @Override
    public void deleteRating(int id) {
        ratingRepository.deleteById(id);
    }
}
