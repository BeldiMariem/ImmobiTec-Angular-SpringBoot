package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IServiceImp {
    public Service addService(Service service, int id_user);
    public void deleteService(int id);
    public Service updateService(Service service);
    public List<Service> getAllService();

    public Service getServiceByID(int id);

    public Service likeService(int id_service, int id_user);
    public Service dislikeService(int id_service, int id_user);


    public List<Service> findBestService();

    public List<Service> getLikedServices(int id_user);
    public float updatePrice(int id_service);
    public Service recommendService(AppUser user);
    public List<Service> searchService(float minPrice,float maxPrice);

    public void addImage(int id, MultipartFile image) throws IOException;

    


}
