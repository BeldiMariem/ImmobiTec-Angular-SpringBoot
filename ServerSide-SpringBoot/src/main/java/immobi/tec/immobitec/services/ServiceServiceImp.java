package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Service;
import immobi.tec.immobitec.repositories.ServiceRepositories;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceServiceImp implements IServiceImp {
    ServiceRepositories serviceRepositories;
    UserRepository userRepository;


    @Override
    public Service addService(Service service, int id_user) {
        AppUser user = userRepository.findById(id_user).orElse(null);
        service.setUser(user);
        return serviceRepositories.save(service);
    }

    @Override
    public void deleteService(int id) {
        serviceRepositories.deleteById(id);

    }

    @Override
    public Service updateService(Service service) {
        return serviceRepositories.save(service);
    }

    @Override
    public List<Service> getAllService() {
        return serviceRepositories.findAll();
    }

    @Override
    public Service getServiceByID(int id) {
        return serviceRepositories.findById(id).orElse(null);
    }

    @Override
    public Service likeService(int id_service, int id_user) {
        Optional<Service> serviceOptional = serviceRepositories.findById(id_service);
        if (!serviceOptional.isPresent()) {
            throw new IllegalArgumentException("Service with ID " + id_service + " not found");
        }


        Service service = serviceOptional.get();
/*
        if (Boolean.TRUE.equals(service.getIsLiked())) {

            throw new IllegalArgumentException("User has already liked this service");
        }
        */


        if (service.getLikes().contains(id_user)) {

            throw new IllegalArgumentException("User has already liked this service");
        }
        if (service.getDislikes().contains(id_user)) {

            service.setIsDisliked(service.getIsDisliked() - 1);
            service.getDislikes().remove(service.getDislikes().indexOf(id_user));
        }

        service.getLikes().add(id_user);

        service.setIsLiked(service.getIsLiked() + 1);
        return serviceRepositories.save(service);
    }

    @Override
    public Service dislikeService(int id_service, int id_user) {
        Service service = serviceRepositories.findById(id_service).orElse(null);

        if (service == null) {

            return null;
        }

/*
        if (service.getIsLiked() != null && !service.getIsLiked()) {
            // User has already disliked the service
            throw new IllegalArgumentException("User has already Disliked this service");
        }
*/

        if (service.getDislikes().contains(id_user)) {
            throw new IllegalArgumentException("User has already Disliked this service");
        }
        if (service.getLikes().contains(id_user)) {

            service.setIsLiked(service.getIsLiked() - 1);
            service.getLikes().remove(service.getLikes().indexOf(id_user));
            //service.setLikes(Collections.singletonList(service.getLikes().remove(id_user)));

        }


        //service.setIsLiked(false);
        service.getDislikes().add(id_user);

        service.setIsDisliked(service.getIsDisliked() + 1);
        return serviceRepositories.save(service);
    }

    @Override
    public List<Service> findBestService() {

        List<Service> services = serviceRepositories.findAll();
        List<Service> bestServices = new ArrayList<>();
        int bestDifference = 0;

        for (Service service : services) {
            int difference = service.getIsLiked() - service.getIsDisliked();
            if (bestServices.isEmpty() || difference > bestDifference) {
                bestServices.clear();
                bestServices.add(service);
                bestDifference = difference;
            } else if (difference == bestDifference) {
                bestServices.add(service);
            }
        }

        return bestServices;





/*
    @Override
    public List<Service> GetBestService() {

        List<Service> services = serviceRepositories.findAll();
        services.sort((s1, s2) -> s2.getIsLiked() - s1.getIsLiked());
        List<Service> bestServices = new ArrayList<>();
        int maxLikes = services.get(0).getIsLiked();
        for (Service service : services) {
            if (service.getIsLiked() == maxLikes) {
                bestServices.add(service);
            } else {
                break;
            }
        }
        return bestServices;
    }


    }

 */
    }

    @Override
    public List<Service> getLikedServices(int id_user) {
        List<Service> likedServices = new ArrayList<>();
        List<Service> allServices = serviceRepositories.findAll();

        for (Service service : allServices) {
            if (service.getLikes().contains(id_user)) {
                likedServices.add(service);
            }
        }

        return likedServices;
    }

    @Override
    @Transactional
    public float updatePrice(int id_service) {
        Optional<Service> optionalService = serviceRepositories.findById(id_service);
        if (!optionalService.isPresent()) {
            return 0.0f; // or throw a custom exception
        }

        Service service = optionalService.get();
        int numAppointments = service.getAppointements().size();
        float newPrice = (float) (numAppointments * service.getPrice());

        return newPrice;
    }

    @Override
    public Service recommendService(AppUser user) {
        List<Service> services = serviceRepositories.findAll();

        // Filter services to exclude ones the user has disliked
        List<Service> filteredServices = services.stream()
                .filter(service -> !service.getDislikes().contains(user))
                .collect(Collectors.toList());

        // Sort filtered services by difference between likes and dislikes, descending
        filteredServices.sort((s1, s2) -> (s2.getIsLiked() - s2.getIsDisliked()) - (s1.getIsLiked() - s1.getIsDisliked()));

        // Iterate over filtered services and recommend the first one with more likes than dislikes
        for (Service service : filteredServices) {
            if (service.getIsLiked() > service.getIsDisliked()) {
                return service;
            }
        }

        // If no services with more likes than dislikes are found, return null
        return null;
    }

    @Override
    public List<Service> searchService(float minPrice, float maxPrice) {
        return serviceRepositories.search(minPrice,maxPrice);
    }

    @Override
    public void addImage(int id, MultipartFile image) throws IOException {
        Service service = serviceRepositories.findById(id).orElse(null);

        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        service.setPicture(Base64.getEncoder().encodeToString(image.getBytes()));

        serviceRepositories.save(service);
    }

    }








