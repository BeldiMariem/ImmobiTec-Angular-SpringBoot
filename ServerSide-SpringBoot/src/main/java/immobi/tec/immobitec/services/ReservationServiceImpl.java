package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Availability;
import immobi.tec.immobitec.entities.DormRoom;
import immobi.tec.immobitec.entities.Reservation;
import immobi.tec.immobitec.repositories.DormRoomRepository;
import immobi.tec.immobitec.repositories.ReservationRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {
    ReservationRepository reservationRepository;
    UserRepository userRepository;
    DormRoomRepository dormRoomRepository;

    @Override
    public Reservation addReservation(Reservation r, int id_User, int id_Room) {
        AppUser au = userRepository.findById(id_User).orElse(null);
        DormRoom d = dormRoomRepository.findById(id_Room).orElse(null);
        if (d.getStatus() == Availability.Available && d.getNbPlace() != 0) {
            r.setDormRoom(d);
            r.setAppUser(au);
            r.setDate_reservation(new Date());
            d.getReservations().add(r);
            d.setNbPlace(d.getNbPlace() - 1);

            if (d.getNbPlace() == 0) {
                d.setStatus(Availability.valueOf("notavailable"));
            }

            return reservationRepository.save(r);
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationByID(int id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    @Transactional
    public void deleteReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        // Update the number of available places in the room
        DormRoom room = reservation.getDormRoom();
        room.setNbPlace(room.getNbPlace() + 1);
        dormRoomRepository.save(room);

        // Delete the reservation record
        reservationRepository.deleteReservationById(id);
        /*
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        // Increase the number of available places in the room that was reserved
        DormRoom reservedRoom = reservation.getDormRoom();
        reservedRoom.setNbPlace(reservedRoom.getNbPlace() + 1);
        dormRoomRepository.save(reservedRoom);

        // Delete the reservation
        reservationRepository.delete(reservation); */
    }

    @Override
    public String getMostReservedMonth() {
        List<Reservation> reservations = new ArrayList<>(reservationRepository.findAll());
        Map<String,Integer> reservationsByMonth = new TreeMap<>();
        for (Reservation reservation : reservations){
            LocalDate reservationDate = reservation.getDate_reservation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String month = reservationDate.getMonth().toString();
            reservationsByMonth.merge(month, 1, Integer::sum);
        }
        String mostReservedMonth = Collections.max(reservationsByMonth.entrySet(), Map.Entry.comparingByValue()).getKey();

        return mostReservedMonth;
    }

    @Override
    public int calculateTotalRevenue(List<Reservation> reservations) {
        int totalRevenue = 0;
        for (Reservation reservation : reservations) {
            if (reservation.getCheckindate() != null && reservation.getCheckoutdate() != null) {
                int numberOfNights = reservation.getNumber_nights();
                int totalPrice = reservation.getDormRoom().getPrice() * numberOfNights;
                totalRevenue += totalPrice;
            }
        }
        return totalRevenue;
    }



    /*@Override
    public int calculateTotalRevenue() {
        int totalRevenue = 0;
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation : reservations) {
            if (reservation.getCheckindate() != null && reservation.getCheckoutdate() != null) {
                int numberOfNights = reservation.getNumber_nights();
                int totalPrice = reservation.getDormRoom().getPrice() * numberOfNights;
                totalRevenue += totalPrice;
            }
        }
        return totalRevenue;
    } */


}
