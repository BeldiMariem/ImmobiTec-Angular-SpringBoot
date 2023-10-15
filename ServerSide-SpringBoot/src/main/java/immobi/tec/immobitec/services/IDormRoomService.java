package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Availability;
import immobi.tec.immobitec.entities.DormRoom;
import immobi.tec.immobitec.entities.ImageProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IDormRoomService {
    public DormRoom addDormRoom(DormRoom d);
    public List<DormRoom> getAllRooms();
    public DormRoom getRoomById(int id);
    public DormRoom updateDormRoom(DormRoom d);
    public void deleteDormRoom(int id);

    public DormRoom likeDormRoom(int id_Room, int id_user);
    public DormRoom dislikeDormRoom(int id_Room, int id_user);

    public List<DormRoom> findBestService();

    long getTotalRooms();

    long getOccupiedRooms();

    long getAvailableRooms();

    public void addImage(int id, MultipartFile image) throws IOException;




}
