package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.Rolee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Rolee,Integer> {
    Rolee findRoleByName(String name);
}
