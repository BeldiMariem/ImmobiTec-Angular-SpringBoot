package immobi.tec.immobitec.controllers;


import immobi.tec.immobitec.entities.ImageProperty;
import immobi.tec.immobitec.entities.Property;
import immobi.tec.immobitec.repositories.PropertyImageRepositoriy;
import immobi.tec.immobitec.services.IAnnouncement;
import immobi.tec.immobitec.services.IProperty;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/property")
public class PropertyController {

    IProperty iProperty;

    PropertyImageRepositoriy propertyImageRepositoriy;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllProperties")
    public List<Property> getAllProperties(){
        return  iProperty.getAllProperties();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addProperty/{id}")
    @ResponseBody
    public Property addProperty(@PathVariable int id,@RequestBody Property p){
        return iProperty.addProperty(id,p);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateProperty/{idAnn}")
    @ResponseBody
    public Property updateProperty(@RequestBody Property p ,@PathVariable int idAnn){
        return iProperty.updateProperty(p,idAnn);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteProperty/{id}")
    public void deleteProperty(@PathVariable("id") int id){
        iProperty.deleteProperty(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getPropertyById/{id}")
    public Property getPropertyByID(@PathVariable("id") int id){
        return iProperty.getPropertyById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/addImage/{id}", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ImageProperty addImage(@RequestParam MultipartFile file,@PathVariable int id) throws IOException {
        return iProperty.addImage(id,file);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteImage/{id}")
    public void deleteImage(@PathVariable int id) {
        iProperty.deleteImage(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/CloseProperty/{id}")
    public Property CloseProperty(@PathVariable int id){
        return iProperty.CloseProperty(id);
    }



}
