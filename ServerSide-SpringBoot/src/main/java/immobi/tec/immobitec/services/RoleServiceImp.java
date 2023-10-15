package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Rolee;
import immobi.tec.immobitec.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImp implements IRoleService {
    RoleRepository roleRepository;
    @Override
    public Rolee getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Rolee> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Rolee addRole(Rolee role) {
        return roleRepository.save(role);
    }

    @Override
    public Rolee updateRole(Rolee role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}
