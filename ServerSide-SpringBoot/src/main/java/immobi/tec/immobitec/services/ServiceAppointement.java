package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Appointement;
import immobi.tec.immobitec.entities.Service;
import immobi.tec.immobitec.repositories.AppointmentRepositories;
import immobi.tec.immobitec.repositories.ServiceRepositories;
import immobi.tec.immobitec.repositories.UserRepository;
import lombok.AllArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceAppointement implements IAppointement{

AppointmentRepositories appointmentRepositories;
UserRepository userRepository;
ServiceRepositories serviceRepositories;

    @Override
    public Appointement addAndAssignAppointementtoUserAndService(Appointement a, int idUser, int idService) {
        AppUser App = userRepository.findById(idUser).orElse(null);
        Service service = serviceRepositories.findById(idService).orElse(null);
        a.setService(service);
        a.setUser(App);
        return appointmentRepositories.save(a);
    }

    @Override
    @Transactional
    public void deleteAppointement(LocalDate currentDate) {
        java.sql.Date date = java.sql.Date.valueOf(currentDate);
        appointmentRepositories.deleteAppointmentsByDateBefore(date);
    }

    @Override
    public void deleteAppointementById(int id) {
        appointmentRepositories.deleteById(id);
    }


    @Override
    public Appointement updateAppointement(Appointement appointement) {
        return appointmentRepositories.save(appointement);
    }

    @Override
    public List<Appointement> getAllAppointement() {
        return appointmentRepositories.findAll();
    }

    @Override
    public Appointement getAppointementByID(int id) {
        return appointmentRepositories.findById(id).orElse(null);
    }


}
