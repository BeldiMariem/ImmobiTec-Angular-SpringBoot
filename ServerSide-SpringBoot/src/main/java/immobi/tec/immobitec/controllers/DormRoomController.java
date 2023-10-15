package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.DormRoom;
import immobi.tec.immobitec.entities.ImageProperty;
import immobi.tec.immobitec.services.IDormRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:4200")

@RequestMapping("/dorm")
public class DormRoomController {
    IDormRoomService iDormRoomService;


    @PutMapping(path = "/addImage/{id}", consumes = {MULTIPART_FORM_DATA_VALUE})
    public void addImage(@RequestParam MultipartFile file, @PathVariable int id) throws IOException {
    iDormRoomService.addImage(id , file);
    }


    @PostMapping("/addDormRoom")
    @ResponseBody
    public DormRoom addDormRoom(@RequestBody DormRoom d){
        return iDormRoomService.addDormRoom(d);
    }



    @GetMapping("/getAllRooms")
    public List<DormRoom> getAllRooms(){
        return iDormRoomService.getAllRooms();
    }

    @GetMapping("/getRoomById/{id}")
    public DormRoom getRoomById(@PathVariable("id") int id){
        return iDormRoomService.getRoomById(id);
    }

    @PutMapping("/updateDormRoom")
    @ResponseBody
    public DormRoom updateDormRoom(@RequestBody DormRoom d){
        return iDormRoomService.updateDormRoom(d);
    }


    @DeleteMapping("/deleteDormRoom/{id}")
    public void deleteDormRoom(@PathVariable("id") int id){
        iDormRoomService.deleteDormRoom(id);
    }


    @PostMapping("/likeDormRoom/{id_Room}/{id_User}")
    @ResponseBody
    public ResponseEntity<DormRoom> likeDormRoom(@PathVariable("id_Room") int id_Room, @PathVariable("id_User") int id_user) {
        DormRoom dormRoom = iDormRoomService.likeDormRoom(id_Room,id_user);
        return ResponseEntity.ok(dormRoom);
    }

    @PostMapping("/dislikeDormRoom/{id_Room}/{id_User}")
    @ResponseBody
    public ResponseEntity<DormRoom> dislikeService (@PathVariable("id_Room") int id_service, @PathVariable("id_User") int id_user) {
        DormRoom dormRoom = iDormRoomService.dislikeDormRoom(id_service,id_user);
        return ResponseEntity.ok(dormRoom);
    }
    @GetMapping("/bestRooms")
    public ResponseEntity<List<DormRoom>> getBestServices() {
        List<DormRoom> bestRooms = iDormRoomService.findBestService();
        if (bestRooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bestRooms);
        }
    }

    @GetMapping("/occupancy-rate")
    public ResponseEntity<Map<String, Object>> getOccupancyRate() {
        long totalRooms = iDormRoomService.getTotalRooms();
        long availableRooms = iDormRoomService.getAvailableRooms();
        double occupancyRate = (double) (totalRooms - availableRooms) / totalRooms * 100;
        String responseMessage = String.format("The occupancy rate of the rooms is %.2f%%", occupancyRate);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> response = new HashMap<>();
        response.put("occupancyRate", occupancyRate);
        response.put("message", responseMessage);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
