package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Property;
import immobi.tec.immobitec.entities.Rating;
import immobi.tec.immobitec.services.IRating;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/Rating")
public class RatingController {

    IRating iRating;

    @GetMapping("/getAllRatings")
    public List<Rating> getAllRatings(){
        return  iRating.getAllRatings();
    }

    @PostMapping("/addRating/{id}/{id_user}")
    @ResponseBody
    public Rating addRatingetAffecterToAnnonce(@PathVariable int id_user,@PathVariable int id,@RequestBody Rating r){
        return iRating.addRating(id_user,id,r);
    }

    @PutMapping("/updateRating")
    @ResponseBody
    public Rating updateRating(@RequestBody Rating r){
        return iRating.updateRating(r);
    }

    @DeleteMapping("/deleteRating/{id}")
    public void deleteRating(@PathVariable("id") int id){
        iRating.deleteRating(id);
    }

    @GetMapping("/getRatingById/{id}")
    public Rating getRatingByID(@PathVariable("id") int id){
        return iRating.getRatingById(id);
    }



}
