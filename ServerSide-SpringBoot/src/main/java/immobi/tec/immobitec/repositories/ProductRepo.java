package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
