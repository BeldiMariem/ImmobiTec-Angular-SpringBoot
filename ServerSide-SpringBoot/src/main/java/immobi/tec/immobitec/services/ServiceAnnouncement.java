package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.*;
import immobi.tec.immobitec.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceAnnouncement implements IAnnouncement{

    AnnouncementRepository announcementRepository;
    PropertyRepository propertyRepository;

    AppointmentRepositories appointmentRepositories;
    UserRepository userRepository;

    researchRepository researchRepository;
    EmailSenderService service;

    @Override
    public Announcement getAnnouncementById(int id) {
        return announcementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Announcement> getAllAnnouncements() {

        List<Announcement> announcements = announcementRepository.retrieveAnnouncements();
        List<Announcement> announcements1 = new ArrayList<>();
        for (Announcement a: announcements) {
            if(a.getBoosted()){
                announcements1.add(0,a);
            } else {
                announcements1.add(a);
            }
        }
        return announcements1;
    }

    @Override
    public List<Announcement> getAllAnnouncementsTWO() {
        return announcementRepository.findAll();
    }

    @Override
    public List<Appointement> getAppointementsByProperty(int id) {
        Property property = propertyRepository.findById(id).orElse(null);
        List<Appointement> appointements1 = appointmentRepositories.findAll();
        List<Appointement> appointements = new ArrayList<>();
        for (Appointement a: appointements1 ){
            if(a.getProperty() == property){
                appointements.add(a);
            }
        }
        return appointements;
    }

    @Override
    public Announcement addAnnouncement(int id,Announcement a) {
        a.setRatmoyenne(0);
        a.setValidite(true);
        a.setPaiement(false);
        a.setBoosted(false);
        Property property = propertyRepository.findById(id).orElse(null);
        property.setAnnouncement(a);
        return announcementRepository.save(a);
    }

    @Override
    public Appointement addAppointement(int idUser,int id, Appointement a)throws ParseException {
        Date d = new Date(System.currentTimeMillis());
        if(a.getDate_appointement().before(d)){
            return null;
        }
        List<Appointement> appointements2 = announcementRepository.retrieveAppointementsByProperty(id);
        List<Appointement> appointements = new ArrayList<>();
        Property property = propertyRepository.findById(id).orElse(null);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dt2 = df.format(a.getDate_appointement());
        for (Appointement ap: appointements2 ){
            String dt =  df.format(ap.getDate_appointement());
            if(dt.equals(dt2)){
                appointements.add(ap);
            }
        }
        for (Appointement ap: appointements ){
            long elapsedms = Math.abs(ap.getDate_appointement().getTime() - a.getDate_appointement().getTime());
            long diff = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
            if(diff< 120){
                System.out.println("Can't add this appointement !!!");
                return null;
            }
        }
        AppUser User = userRepository.findById(idUser).orElse(null);
        a.setProperty(property);
        a.setUser(User);
        service.sendSimpleEmail(property.getUser().getEmail(),"Appointement added : Date : "+a.getDate_appointement(),"Appointement !!");
        return appointmentRepositories.save(a);
    }

    @Override
    public String DeleteAppointement(int id) {
        Appointement appointement = announcementRepository.retrieveAppointementsById(id);
        Date d = new Date(System.currentTimeMillis());
        long elapsedms = Math.abs(d.getTime() - appointement.getDate_appointement().getTime());
        long diff = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
        if(diff>=1440){
            appointmentRepositories.deleteById(id);
            return "Rendez vous Supprimer avec Succes !!";
        }

        return "null";


    }

    @Override
    public Announcement updateAnnouncement(Announcement a) {
        return announcementRepository.save(a);
    }

    @Override
    public void deleteAnnouncement(int id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public List<Announcement> TopAnnouncements() {
        return announcementRepository.TopAnnouncements();
    }

    @Override
    public List<Announcement> INVALIDAnnouncements() {
        return announcementRepository.retrieveInvalidAnnouncements();
    }


    @Override
    public Appointement updateAppointement(Appointement a) throws ParseException{
        Date d = new Date(System.currentTimeMillis());
        if(a.getDate_appointement().before(d)){
            return null;
        }
        Property p = announcementRepository.retrieveProperty(a.getId_appointement());
        AppUser u = announcementRepository.retrieveUser(a.getId_appointement());
        List<Appointement> appointements2 = announcementRepository.retrieveAppointementsByProperty(p.getId_property());
        List<Appointement> appointements = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dt2 = df.format(a.getDate_appointement());
        for (Appointement ap: appointements2 ){
            String dt =  df.format(ap.getDate_appointement());
            if(dt.equals(dt2)&&(a.getId_appointement()!=ap.getId_appointement())){
                appointements.add(ap);
            }
        }
        for (Appointement ap: appointements ){
            long elapsedms = Math.abs(ap.getDate_appointement().getTime() - a.getDate_appointement().getTime());
            long diff = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
            if(diff< 120){
                System.out.println("Can't add this appointement !!!");
                return null;
            }
        }
        a.setProperty(p);
        a.setUser(u);
        return appointmentRepositories.save(a);
    }

    @Override
    public List<Announcement> SuggestedAnnouncements(int id_user) {
        List<researches> userResearches = researchRepository.retrieveresearchesByUser(id_user);
        List<Property> properties = propertyRepository.findAll();
        List<Announcement> suggestedAnnouncements = new ArrayList<>();

        for (Property p: properties ){
            for (researches r: userResearches ){
                String s1[]=r.getSearchPhrase().split("[ ]+");
                for (String s: s1 ){
                    if(p.getDescription().toLowerCase().contains(s.toLowerCase()) || p.getAdresse().getCity().toLowerCase().equals(s.toLowerCase())){
                        if(!suggestedAnnouncements.contains(p.getAnnouncement())){
                            suggestedAnnouncements.add(p.getAnnouncement());
                        }
                    }
                }
            }
        }
        return suggestedAnnouncements;
    }

    @Override
    public researches addResearch(int id_user, researches r) {
        AppUser user = userRepository.findById(id_user).orElse(null);
        List<researches> researchess = researchRepository.retrieveresearchesByUser(id_user);
        for (researches re: researchess ){
         if(re.getSearchPhrase().toLowerCase().equals(r.getSearchPhrase().toLowerCase())){
             return null;
         }
        }
        r.setUser(user);
        return researchRepository.save(r);
    }

    @Override
    public Announcement valideAnnouncement(int id_announcement) {
        Announcement announcement = announcementRepository.findById(id_announcement).orElse(null);
        announcement.setValidite(true);
        return announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> SaleAnnouncements() {
        List<Announcement> announcements = announcementRepository.SaleAnnouncements();
        List<Announcement> announcements1 = new ArrayList<>();
        for (Announcement a: announcements) {
            if(a.getBoosted()){
                announcements1.add(0,a);
            } else {
                announcements1.add(a);
            }
        }
        return announcements1;
    }

    @Override
    public List<Announcement> RentAnnouncements() {
        List<Announcement> announcements = announcementRepository.RentAnnouncements();
        List<Announcement> announcements2 = new ArrayList<>();
        for (Announcement a: announcements) {
            if(a.getBoosted()){
                announcements2.add(0,a);
            } else {
                announcements2.add(a);
            }
        }
        return announcements2;
    }

    @Override
    public List<Announcement> FilterAnnouncements(String city, String typeAnnouncement, float MaxBudget, float MinBudget) {
        List<Property> properties = propertyRepository.findAll();
        List<Property> propertiess = new ArrayList<>();
        propertiess = properties.stream().filter(pr -> pr.getAnnouncement() != null && pr.getAnnouncement().isValidite() && !pr.getClosed()).collect(Collectors.toList());
        List<Property> properties1 = new ArrayList<>();
        List<Announcement> announcements = new ArrayList<>();
        if(!city.equals("")){
            properties1 = propertiess.stream().filter(pr -> pr.getAdresse().getCity().equals(city)).collect(Collectors.toList());
            if(!typeAnnouncement.equals("")){
                properties1 = properties1.stream().filter(pr -> pr.getAnnouncement().getType().toString().equals(typeAnnouncement)).collect(Collectors.toList());
            }
            if(MaxBudget != 0.0){
                properties1 = properties1.stream().filter(pr -> pr.getAnnouncement().getPrice()<=MaxBudget).collect(Collectors.toList());
            } if(MinBudget != 0.0){
                properties1 = properties1.stream().filter(pr -> pr.getAnnouncement().getPrice()>=MinBudget).collect(Collectors.toList());
            }
        }else
        if(!typeAnnouncement.equals("")){
            properties1 = propertiess.stream().filter(pr -> pr.getAnnouncement().getType().toString().equals(typeAnnouncement)).collect(Collectors.toList());
            if(MaxBudget != 0.0){
                properties1 = properties1.stream().filter(pr -> pr.getAnnouncement().getPrice()<=MaxBudget).collect(Collectors.toList());
            }
            if(MinBudget != 0.0){
                properties1 = properties1.stream().filter(pr -> pr.getAnnouncement().getPrice()>=MinBudget).collect(Collectors.toList());
            }
        }else if(MaxBudget != 0.0){
            properties1 = propertiess.stream().filter(pr -> pr.getAnnouncement().getPrice()<=MaxBudget).collect(Collectors.toList());
            if(MinBudget != 0.0){
                properties1 = properties1.stream().filter(pr -> pr.getAnnouncement().getPrice()>=MinBudget).collect(Collectors.toList());
            }
        }
        for (Property pr: properties1 ){
            announcements.add(pr.getAnnouncement());
        }
        return announcements;
    }



    @Override
    @Scheduled(cron = "*/60 * * * * *" )
    public void updateRatings() {
        List<Announcement> announcements = announcementRepository.retrieveAnnouncements();
        for (Announcement a: announcements) {
            if(announcementRepository.retrieveRatings(a.getId_announcement()).size() != 0){
                float moy = announcementRepository.retrieveSumRatings(a.getId_announcement())/announcementRepository.retrieveRatings(a.getId_announcement()).size();
                a.setRatmoyenne(moy);
                announcementRepository.save(a);
            }else{
                a.setRatmoyenne(0);
                announcementRepository.save(a);
            }
        }
    }


}
