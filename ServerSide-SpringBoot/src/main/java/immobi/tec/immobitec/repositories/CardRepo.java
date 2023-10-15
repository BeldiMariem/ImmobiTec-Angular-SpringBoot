package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card, Integer> {
}
