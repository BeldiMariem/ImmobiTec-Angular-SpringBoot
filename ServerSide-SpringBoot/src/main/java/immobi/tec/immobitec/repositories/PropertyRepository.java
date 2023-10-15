package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property,Integer> {
}
