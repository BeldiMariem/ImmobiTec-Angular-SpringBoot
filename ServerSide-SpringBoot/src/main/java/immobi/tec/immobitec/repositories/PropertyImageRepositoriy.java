package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Announcement;
import immobi.tec.immobitec.entities.ImageProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyImageRepositoriy extends JpaRepository<ImageProperty,Integer> {
}
