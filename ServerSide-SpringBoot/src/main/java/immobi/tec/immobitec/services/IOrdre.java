package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Ordre;

import java.util.List;

public interface IOrdre {
    public Ordre addOrdre(Ordre o);
    public List<Ordre> getAllOrdres();
    public Ordre getOrdreByid(int id_ordre);
    public void deleteOrdre(int id_ordre);

    public Ordre updateOrdre(Ordre o);
}
