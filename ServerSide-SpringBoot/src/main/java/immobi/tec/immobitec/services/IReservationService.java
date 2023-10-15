package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Reservation;

import java.util.List;

public interface IReservationService {
    public Reservation addReservation(Reservation r, int id_User , int id_Room);
    public List<Reservation> getAllReservations();
    public Reservation getReservationByID(int id);
    public Reservation updateReservation(Reservation r);
    public void deleteReservation(int id);
    public String getMostReservedMonth();
    public int calculateTotalRevenue(List<Reservation> reservations);

}

