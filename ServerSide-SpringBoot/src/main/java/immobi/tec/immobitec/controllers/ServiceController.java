package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Service;
import immobi.tec.immobitec.repositories.UserRepository;
import immobi.tec.immobitec.services.IServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/service")
public class ServiceController {
    IServiceImp iServiceImp;
    UserRepository userRepository;

    @PostMapping("/addService/{id_user}")
    @ResponseBody
    public Service addService(@RequestBody Service service,@PathVariable("id_user")int id_user){
        return iServiceImp.addService(service,id_user);
    }
    @DeleteMapping("/deleteService/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    public void deleteService(@PathVariable("id") int id ){
        iServiceImp.deleteService(id);
    }

    @GetMapping("/getServiceByID/{id}")
    @ResponseBody
    public Service getServiceByID(@PathVariable int id){
        return iServiceImp.getServiceByID(id);
    }

    @PutMapping("/updateService")
    @ResponseBody
    public Service updateService(@RequestBody Service service){return iServiceImp.updateService(service);}

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllService")
    @ResponseBody
    public List<Service> getAllService(){return iServiceImp.getAllService();}


    @PostMapping("/likeService/{id_Service}/{id_User}")
    @CrossOrigin(origins = "http://localhost:4200")
    @ResponseBody
    public ResponseEntity<Service> likeService(@PathVariable("id_Service") int id_service, @PathVariable("id_User") int id_user) {
        Service service = iServiceImp.likeService(id_service, id_user);
        return ResponseEntity.ok(service);
    }

    @PostMapping("/dislikeService/{id_Service}/{id_User}")
    @ResponseBody
    public ResponseEntity<Service> dislikeService (@PathVariable("id_Service") int id_service, @PathVariable("id_User") int id_user) {
        Service service = iServiceImp.dislikeService(id_service, id_user);
        return ResponseEntity.ok(service);
    }
    @GetMapping("/bestservices")
    public ResponseEntity<List<Service>> getBestServices() {
        List<Service> bestServices = iServiceImp.findBestService();
        if (bestServices.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bestServices);
        }
    }
    @GetMapping("/services/liked/{id_user}")
    public List<Service> getLikedServices(@PathVariable int id_user) {
        return iServiceImp.getLikedServices(id_user);
    }

    @GetMapping("/{id_service}/price")
    public ResponseEntity<Float> updatePrice(@PathVariable(value = "id_service") int id_service) {
        float newPrice = iServiceImp.updatePrice(id_service);
        return ResponseEntity.ok().body(newPrice);
    }
    @GetMapping("/users/{userId}/recommendService")
    public ResponseEntity<Service> recommendService(@PathVariable int userId) {
        Optional<AppUser> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            // Return 404 if user is not found
            return ResponseEntity.notFound().build();
        }

        AppUser user = userOptional.get();

        // Call recommendService() to get the recommended service
        Service recommendedService = iServiceImp.recommendService(user);

        if (recommendedService == null) {
            // Return 404 if no service is recommended
            return ResponseEntity.notFound().build();
        }

        // Return the recommended service
        return ResponseEntity.ok(recommendedService);
    }
    @GetMapping("/search/{minPrice}/{maxPrice}")
    public ResponseEntity<List<Service>> searchServices(
                                                        @PathVariable(required = false) float minPrice,
                                                        @PathVariable(required = false) float maxPrice) {
        List<Service> services = iServiceImp.searchService(minPrice,maxPrice);
        return ResponseEntity.ok(services);
    }
    @PutMapping(path = "/addImages/{id}", consumes = {MULTIPART_FORM_DATA_VALUE})
    public void addImage(@RequestParam MultipartFile file, @PathVariable int id) throws IOException {
        iServiceImp.addImage(id , file);
    }

    // Other controller endpoints...
}




