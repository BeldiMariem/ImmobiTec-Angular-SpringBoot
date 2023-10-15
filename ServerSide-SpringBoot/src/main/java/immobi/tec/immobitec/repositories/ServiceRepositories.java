package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepositories extends JpaRepository<Service,Integer> {
    @Query("SELECT s FROM Service s WHERE " +
            "(:minPrice IS NULL OR s.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR s.price <= :maxPrice)")
    List<Service> search(
                         @Param("minPrice") float minPrice,
                         @Param("maxPrice") float maxPrice);
}
