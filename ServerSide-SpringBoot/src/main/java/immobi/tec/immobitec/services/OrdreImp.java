package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Ordre;
import immobi.tec.immobitec.repositories.OrdreRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdreImp implements IOrdre{
     @Autowired
    OrdreRepo  ordreRepo;


    @Override
    public Ordre addOrdre(Ordre o) {
        return ordreRepo.save(o);
    }

    @Override
    public List<Ordre> getAllOrdres() {
        return ordreRepo.findAll();
    }

    @Override
    public Ordre getOrdreByid(int id_ordre) {
        return ordreRepo.findById(id_ordre).orElse(null);
    }

    @Override
    public void deleteOrdre(int id_ordre) {
        ordreRepo.deleteById(id_ordre);
    }

    @Override
    public Ordre updateOrdre(Ordre o) {
        return ordreRepo.save(o);
    }


}
