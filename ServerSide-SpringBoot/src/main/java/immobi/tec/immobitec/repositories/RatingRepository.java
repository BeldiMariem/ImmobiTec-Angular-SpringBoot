package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Property;
import immobi.tec.immobitec.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Integer> {



}
