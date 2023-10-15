package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Appointement;
import immobi.tec.immobitec.entities.Service;

import java.time.LocalDate;
import java.util.List;

public interface IAppointement {
    public Appointement addAndAssignAppointementtoUserAndService(Appointement a,int idUser,int idService);
    public void deleteAppointement(LocalDate currentDate);
    public void deleteAppointementById(int id);
    public Appointement updateAppointement(Appointement appointement);
    public List<Appointement> getAllAppointement();
    public Appointement getAppointementByID(int id);




}
