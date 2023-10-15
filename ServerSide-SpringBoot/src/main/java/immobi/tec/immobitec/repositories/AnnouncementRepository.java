package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

    @Query("SELECT Sum(r.note) FROM Announcement a, Rating r where a.id_announcement=r.announcement.id_announcement and r.announcement.id_announcement=:id_announcement")
    float retrieveSumRatings(@Param("id_announcement") int id_announcement);


    @Query("SELECT r FROM Announcement a, Rating r where a.id_announcement=r.announcement.id_announcement and r.announcement.id_announcement=:id_announcement")
    List<Rating> retrieveRatings(@Param("id_announcement") int id_announcement);

    @Query("SELECT a FROM Announcement a where  a.validite=true and a.property.closed=false")
    List<Announcement> retrieveAnnouncements();

    @Query("SELECT a FROM Announcement a where  a.validite=false")
    List<Announcement> retrieveInvalidAnnouncements();

    @Query("SELECT a FROM Appointement a, Property p where p.id_property=a.property.id_property and a.property.id_property=:id_property")

    List<Appointement> retrieveAppointementsByProperty(@Param("id_property") int id_property);

    @Query("SELECT p FROM  Property p,Appointement a where p.id_property=a.property.id_property and a.id_appointement=:id_appointement")
    Property retrieveProperty(@Param("id_appointement") int id_appointement);


    @Query("SELECT u FROM  AppUser u,Appointement a where u.id_user=a.user.id_user and a.id_appointement=:id_appointement")
    AppUser retrieveUser(@Param("id_appointement") int id_appointement);
    @Query("SELECT a FROM Announcement a where a.validite=true and a.property.closed=false order by a.ratmoyenne desc ")

    List<Announcement> TopAnnouncements();

    @Query("SELECT a FROM Announcement a where a.type='Sale' and a.validite=true ")
    List<Announcement> SaleAnnouncements();

    @Query("SELECT a FROM Announcement a where a.type='Rent' and a.validite=true ")
    List<Announcement> RentAnnouncements();

    @Query("SELECT a FROM Appointement a where  a.id_appointement=:id_app")

    Appointement retrieveAppointementsById(@Param("id_app") int id_app);
}
