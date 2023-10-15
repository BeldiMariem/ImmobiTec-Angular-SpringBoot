package immobi.tec.immobitec.services;

import immobi.tec.immobitec.entities.Rolee;

import java.util.List;

public interface IRoleService {
    public Rolee getRoleById(int id);

    public List<Rolee> getAllRoles();

    public Rolee addRole(Rolee role);

    public Rolee updateRole(Rolee role);

    public void deleteRole(int id);

}
