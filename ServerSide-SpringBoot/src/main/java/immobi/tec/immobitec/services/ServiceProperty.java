package immobi.tec.immobitec.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import immobi.tec.immobitec.entities.Announcement;
import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.ImageProperty;
import immobi.tec.immobitec.entities.Property;
import immobi.tec.immobitec.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ServiceProperty implements IProperty{

    PropertyRepository propertyRepository;

    AnnouncementRepository announcementRepository;

    UserRepository userRepository;

    PropertyImageRepositoriy propertyImageRepositoriy;

    @Override
    public Property getPropertyById(int id) {
        return propertyRepository.findById(id).orElse(null);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public Property addProperty(int id,Property p) {
        AppUser user = userRepository.findById(id).orElse(null);
        p.setUser(user);
        return propertyRepository.save(p);
    }

    @Override
    public ImageProperty addImage(int id, MultipartFile image) throws IOException {
        Property property = propertyRepository.findById(id).orElse(null);
        ImageProperty i = new ImageProperty();
        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        i.setUrl(Base64.getEncoder().encodeToString(image.getBytes()));
        i.setProperty(property);
        return propertyImageRepositoriy.save(i);
    }

    @Override
    public void deleteImage(int id) {
         propertyImageRepositoriy.deleteById(id);
    }

    @Override
    public Property CloseProperty(int id) {
        Property property = propertyRepository.findById(id).orElse(null);
        if(property.getClosed()== false){
            property.setClosed(true);
        }else{
            property.setClosed(false);
        }
        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Property p , int idAnn) {
        Announcement announcement = announcementRepository.findById(idAnn).orElse(null);
        p.setAnnouncement(announcement);
        return propertyRepository.save(p);
    }

    @Override
    public void deleteProperty(int id) {
        propertyRepository.deleteById(id);
    }


}
