package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.ImageProperty;
import immobi.tec.immobitec.entities.Property;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProperty {

    public Property getPropertyById(int id);

    public List<Property> getAllProperties();

    public Property addProperty(int id,Property p);

    public Property updateProperty(Property p , int idANn);

    public void deleteProperty(int id);

    public void deleteImage(int id);

    public Property CloseProperty(int id);

    public ImageProperty addImage(int id, MultipartFile image) throws IOException;
}
