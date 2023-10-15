package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.*;
import immobi.tec.immobitec.repositories.DormRoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DormRoomServiceImpl implements IDormRoomService {
    DormRoomRepository dormRoomRepository;

    @Override
    public void addImage(int id, MultipartFile image) throws IOException {
        DormRoom dorm = dormRoomRepository.findById(id).orElse(null);

        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        dorm.setPicture(Base64.getEncoder().encodeToString(image.getBytes()));

        dormRoomRepository.save(dorm);
    }

    @Override
    public DormRoom addDormRoom(DormRoom d)
    {
     if(d.getDormRoomType()== DormRoomType.Single){
         d.setNbPlace(1);
     }
     if (d.getDormRoomType()== DormRoomType.Double){
         d.setNbPlace(2);
     }
     if (d.getDormRoomType()==DormRoomType.Triple){
         d.setNbPlace(3);
     }
     if (d.getDormRoomType()==DormRoomType.Quad){
         d.setNbPlace(4);
     }
        return dormRoomRepository.save(d);
    }

    @Override
    public List<DormRoom> getAllRooms() {
        return dormRoomRepository.findAll();
    }

    @Override
    public DormRoom getRoomById(int id) {
        return dormRoomRepository.findById(id).orElse(null);
    }

    @Override
    public DormRoom updateDormRoom(DormRoom d) {
        return dormRoomRepository.save(d);
    }

    @Override
    public void deleteDormRoom(int id) {
    dormRoomRepository.deleteById(id);
    }

    @Override
    public DormRoom likeDormRoom(int id_Room, int id_user) {
        Optional<DormRoom> DormRoomOptional = dormRoomRepository.findById(id_Room);
        if (!DormRoomOptional.isPresent()) {
            throw new IllegalArgumentException("Service with ID " + id_Room + " not found");
        }



        DormRoom dormRoom = DormRoomOptional.get();
        if (dormRoom.getLikes().contains(id_user)) {
            // User has already liked the service
            throw new IllegalArgumentException("User has already liked this DormRoom");
        }
        if (dormRoom.getDislikes().contains(id_user)) {

            dormRoom.setIsDisliked(dormRoom.getIsDisliked() - 1);
            dormRoom.getDislikes().remove(dormRoom.getDislikes().indexOf(id_user));
        }

        dormRoom.getLikes().add(id_user);

        dormRoom.setIsLiked(dormRoom.getIsLiked() + 1);
        return dormRoomRepository.save(dormRoom);
    }

    @Override
    public DormRoom dislikeDormRoom(int id_Room, int id_user) {
        DormRoom dormRoom = dormRoomRepository.findById(id_Room).orElse(null);

        if (dormRoom == null) {
            // Return null or throw a custom exception if required
            return null;
        }
        if (dormRoom.getDislikes().contains(id_user)) {
            // User has already disliked the service
            throw new IllegalArgumentException("User has already Disliked this DormRoom");
        }
        if (dormRoom.getLikes().contains(id_user)) {

            dormRoom.setIsLiked(dormRoom.getIsLiked() - 1);
            dormRoom.getLikes().remove(dormRoom.getLikes().indexOf(id_user));


        }



        dormRoom.getDislikes().add(id_user);

        dormRoom.setIsDisliked(dormRoom.getIsDisliked() + 1);
        return dormRoomRepository.save(dormRoom);
    }

    @Override
    public List<DormRoom> findBestService() {
        List<DormRoom> dormRooms = dormRoomRepository.findAll();
        List<DormRoom> bestRooms = new ArrayList<>();
        int[] bestDifferences = {0, 0, 0};

        for (DormRoom dormRoom : dormRooms) {
            int difference = dormRoom.getIsLiked() - dormRoom.getIsDisliked();
            if(difference <=0){
                continue;
            }
            if (bestRooms.size() < 3) {
                bestRooms.add(dormRoom);
                bestDifferences[bestRooms.size() - 1] = difference;
            } else if (difference >= bestDifferences[2]) {
                bestRooms.remove(2);
                bestRooms.add(dormRoom);
                bestDifferences[2] = difference;
            } else if (difference >= bestDifferences[1]) {
                bestRooms.remove(1);
                bestRooms.add(dormRoom);
                bestDifferences[1] = difference;
            } else if (difference >= bestDifferences[0]) {
                bestRooms.remove(0);
                bestRooms.add(dormRoom);
                bestDifferences[0] = difference;
            }
        }

        return bestRooms;
    }

    @Override
    public long getTotalRooms() {
        return dormRoomRepository.count();
    }

    @Override
    public long getOccupiedRooms() {
        return dormRoomRepository.countByAvailabilityNot(Availability.Available);
    }

    @Override
    public long getAvailableRooms() {
        return dormRoomRepository.countByAvailability(Availability.Available);
    }



}
