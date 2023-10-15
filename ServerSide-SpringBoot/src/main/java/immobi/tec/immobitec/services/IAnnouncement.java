package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Announcement;
import immobi.tec.immobitec.entities.Appointement;
import immobi.tec.immobitec.entities.researches;
import org.springframework.data.repository.query.Param;

import java.text.ParseException;
import java.util.List;

public interface IAnnouncement {

    public Announcement getAnnouncementById(int id);

    public List<Announcement> getAllAnnouncements();

    public List<Announcement> getAllAnnouncementsTWO();

    public List<Appointement> getAppointementsByProperty(int id);

    public Announcement addAnnouncement(int id,Announcement a);

    public Appointement addAppointement(int idUser,int id, Appointement a)throws ParseException;

    public String DeleteAppointement(int id);

    public Announcement updateAnnouncement(Announcement a);

    public Appointement updateAppointement(Appointement a) throws ParseException;

    public void deleteAnnouncement(int id);

    public List<Announcement> TopAnnouncements();

    public List<Announcement> INVALIDAnnouncements();

    public List<Announcement> SuggestedAnnouncements(int id_user);

    public researches addResearch(int id_user, researches r);

    public Announcement valideAnnouncement(int id_announcement);

    public List<Announcement> SaleAnnouncements();

    public List<Announcement> RentAnnouncements();

    public List<Announcement> FilterAnnouncements(String city , String streetName , float MaxBudget , float MinBudget);

    public void updateRatings();

}
