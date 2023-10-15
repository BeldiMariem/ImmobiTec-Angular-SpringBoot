package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.entities.Rolee;
import immobi.tec.immobitec.services.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor


@CrossOrigin(origins = "http://localhost:4200"  ,originPatterns = "Access-Control-Allow-Origin")
public class RoleController {
    IRoleService iRoleService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/role/getAllRoles")
    public List<Rolee> getAllRoles(){
        return  iRoleService.getAllRoles();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllRoles")
    public List<Rolee> getAll(){
        return  iRoleService.getAllRoles();
    }
    @PostMapping("/role/addRole")
    @ResponseBody
    public Rolee addRole(@RequestBody Rolee role){
        return iRoleService.addRole(role);
    }

    @PutMapping("/role/updateRole")
    @ResponseBody
    public Rolee updateRole(@RequestBody Rolee role){
        return iRoleService.updateRole(role);
    }

    @DeleteMapping("/role/deleteRole/{id}")
    public void deleteRole(@PathVariable("id") int id){
        iRoleService.deleteRole(id);
    }

    @GetMapping("/role/getRoleById/{id}")
    public Rolee getRoleByID(@PathVariable("id") int id){
        return iRoleService.getRoleById(id);
    }
}
