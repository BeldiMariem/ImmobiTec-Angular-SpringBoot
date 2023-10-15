package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.DTO.UserDTO;
import immobi.tec.immobitec.Security.JwtBlacklist;
import immobi.tec.immobitec.entities.*;
import immobi.tec.immobitec.repositories.AdresseRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import immobi.tec.immobitec.services.IRoleService;
import immobi.tec.immobitec.services.IUserService;
import immobi.tec.immobitec.services.UserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    AdresseRepository adresseRepository;
    IRoleService iRoleService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    @Autowired
    UserService userService;


    @GetMapping("/user/getAllUsers")
    public ResponseEntity<Page<AppUser>> getAllUsers(
                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "10") int size,
                                                  @RequestParam(name = "sort", defaultValue = "id_user") String sortField,
                                                  @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                  @RequestParam(name = "name") String name,
                                                  @RequestParam(name = "lastname") String lastname,
                                                  @RequestParam(name = "email") String email
                                                 ){
        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }
        UserPage userPage= new UserPage();
        UserSearchCriteria userSearchCriteria = new UserSearchCriteria();
        userPage.setPageNumber(page);userPage.setPageSize(size);
        userPage.setSortBy(sortField);userPage.setSortDirection(Sort.Direction.fromString(direction));
        userSearchCriteria.setName(name);
        userSearchCriteria.setLastname(lastname);
        userSearchCriteria.setEmail(email);
        return new ResponseEntity<>(userService.getUsers(userPage, userSearchCriteria),
                HttpStatus.OK);
    }

    @GetMapping("/user/getAllAdress")
    public List<Adresse> getAllAdress(){

        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }return  adresseRepository.findAll();
    }
    @GetMapping("/user/getAll")
    public List<UserDTO> getAll(){

        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }
        List<UserDTO> usersss=new ArrayList<>();
       Iterable <AppUser> users= userRepository.findAll();
        for (AppUser user : users)
        {
            UserDTO userDTO=modelMapper.map(user, UserDTO.class);
            userDTO.setRole_id(user.getRole().getId());
            userDTO.setRole_name(user.getRole().getName());
            usersss.add(userDTO);

        }
        return usersss;


    }


  /*  @PostMapping("/addUser/{RoleId}")
    @ResponseBody
    public AppUser addUser(@RequestBody AppUser user,@PathVariable("RoleId") int id){
        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }
        Rolee role=iRoleService.getRoleById(id);
        user.setRole(role);
        return iUserService.addUser(user);
    }*/
  @PostMapping("/user/add")
  @ResponseBody
    public AppUser createUser(@RequestBody UserDTO userDTO){
      if (isBlacklisted(AuthenticationController.tokenn)) {
          throw new RuntimeException("INVALID_CREDENTIALS") ;
      }

        return userService.addUser(userDTO);
  }

/*
    @PutMapping("/updateUser")
    @ResponseBody
    public AppUser updateUser(@RequestBody AppUser user){

        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }
        return iUserService.updateUser(user);
    }
    */


    @DeleteMapping("/user/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int id){
        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }
        userRepository.DeleteUser(id);

    }




    @GetMapping("/user/getbyid/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDTO getUserById(@PathVariable int id) {

        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }

        return userService.getUserById(id);
    }
    @GetMapping("/useer/getByEmail/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {

        if (isBlacklisted(AuthenticationController.tokenn)) {
            throw new RuntimeException("INVALID_CREDENTIALS") ;
        }
        AppUser userApp =userRepository.findByEmail(email);
        UserDTO userDTO=modelMapper.map(userApp, UserDTO.class);
        userDTO.setRole_id(userApp.getRole().getId());
        userDTO.setRole_name(userApp.getRole().getName());
        return userDTO;
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updateUser")
    public AppUser updateUserr( @RequestBody UserDTO userDTO){
        return
                userService.update(userDTO);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/updatePass")
    public AppUser updatePass( @RequestBody UserDTO userDTO){
        return
                userService.updatePass(userDTO);
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/user/update")
    public AppUser updateUser( @RequestBody UserDTO userDTO){
        return userService.update(userDTO);
    }
    @PutMapping("/banUser/{email}/{duration}")
    public String banUser(@PathVariable("email") String email,@PathVariable("duration") int duration)
    {
        if (isBlacklisted(AuthenticationController.tokenn)) {
            return "INVALID_CREDENTIALS";
        }
        userService.banUser(email,duration);
        return "cbn";
    }
    public boolean isBlacklisted(String token) {
        return JwtBlacklist.contains(token);
    }


}