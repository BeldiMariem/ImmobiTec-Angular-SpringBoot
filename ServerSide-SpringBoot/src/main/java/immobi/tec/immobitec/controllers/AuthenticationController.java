package immobi.tec.immobitec.controllers;

import immobi.tec.immobitec.DTO.UserDTO;
import immobi.tec.immobitec.Security.*;
import immobi.tec.immobitec.entities.Adresse;
import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.Rolee;
import immobi.tec.immobitec.entities.Session;
import immobi.tec.immobitec.repositories.RoleRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import immobi.tec.immobitec.services.IRoleService;
import immobi.tec.immobitec.services.IUserService;
import immobi.tec.immobitec.services.UserExistException;
import immobi.tec.immobitec.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    IRoleService iRoleService;
    @Autowired
     UserRepository userRepository;
    @Autowired
    Session session;
    @Autowired
    UserService service;
     static String tokenn;
    static String tokenP;
    @Autowired
    RoleRepository roleRepository;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());


         tokenn = jwtTokenUtil.generateToken(userDetails);

        AppUser user = userRepository.findByEmail(userDetails.getUsername());

        session.setEmail(user.getEmail());session.setId_user(user.getId_user());
        session.setName(user.getName());session.setLastname(user.getLastname());
        session.setPassword(user.getPassword()); session.setRole(user.getRole().getName());
        if (user.isEnabled()){
            if (user.isBanned())
            {
         throw new UserExistException("You can't access your account");
            }
            else{

               return  ResponseEntity.ok(new AuthenticationResponse(tokenn));
            }
        }
        else{
            throw new UserExistException("You must verify your account");
          }

    }

    @PostMapping
    @RequestMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return ResponseEntity.ok(service.register(userDTO, "http://localhost:9000/auth/"));
    }
    @PostMapping("/forgot_password")
    @ResponseBody
    public String processForgotPassword(@RequestBody String email, Model model) {
         tokenP =RandomString.make(30);
        try {
            System.out.println(email);
            service.updateResetPasswordToken(tokenP, email);
            String resetPasswordLink = "http://localhost:4200/updatePassword";
            service.sendEmail(email, resetPasswordLink);
            model.addAttribute("We have sent a reset password link to your email. Please check.", "We have sent a reset password link to your email. Please check.");

        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Error while sending email");
        }

        return "We have sent a reset password link to your email. Please check.";
    }
    @PostMapping("/update_password")
    @ResponseBody
    public String processResetPassword( @RequestBody String password, Model model) {
        AppUser customer = service.getByResetPasswordToken(tokenP);
        model.addAttribute("title", "Reset your password");

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            service.updatePassword(customer, password);
            model.addAttribute("suceess update", "You have successfully changed your password.");
        }
        return "suceess update";
    }
    @GetMapping("/reset_password")
    public String showResetPassword(@Param(value = "token") String token, Model model) {
        AppUser customer = service.getByResetPasswordToken(tokenP);
        model.addAttribute("token", token);

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "Invalid token";
        }
        this.tokenn=token;

        return "Success";
    }
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (service.verify(code)) {
            return "verify success you can login now";
        } else {
            return "verify failed";
        }
    }
    @GetMapping("/googleAuth")
    public RedirectView currentUser (OAuth2AuthenticationToken oAuth2AuthenticationToken)
    {String email= oAuth2AuthenticationToken.getPrincipal().getAttribute("email");

        AppUser user = userRepository.findByEmail(email);
        if (user == null) {
            user = new AppUser();
            Adresse a = new Adresse();
            a.setCity("city");
            a.setState("state");
            a.setStreet_name("street name");
            Rolee role = roleRepository.findRoleByName("ROLE_USER");
            user.setEmail(oAuth2AuthenticationToken.getPrincipal().getAttribute("email"));
            user.setRole(role);
            user.setBirthday(new Date());
            user.setAdresse(a);
            user.setName(oAuth2AuthenticationToken.getPrincipal().getAttribute("name"));
            user.setLastname(oAuth2AuthenticationToken.getPrincipal().getAttribute("family_name"));
            user.setPicture(oAuth2AuthenticationToken.getPrincipal().getAttribute("picture"));
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode("test");
            user.setPassword(encodedPassword); user.setEnabled(true);

            userRepository.save(user);
        }
        user.setEnabled(true);
        session.setEmail(user.getEmail());session.setId_user(user.getId_user());
        session.setName(user.getName());session.setLastname(user.getLastname());
        session.setPicture(user.getPicture());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( email, "test"));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
         tokenn = jwtTokenUtil.generateToken(userDetails);
        return new RedirectView("http://localhost:4200/login?email="+email+"&token="+tokenn);

}

    @RequestMapping(value="/lll", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            new SecurityContextLogoutHandler().logout(request, response, auth);

        return "redirect:http://localhost:9000/login";
    }

    @GetMapping("/jwt/logout")
    public RedirectView logout() {
        System.out.println(tokenn);
        JwtBlacklist.add(tokenn);
        return new RedirectView("http://localhost:9000/login");
    }
    @PostMapping(path = "/addImage/{email}", consumes = {MULTIPART_FORM_DATA_VALUE})
    public UserDTO addImage(@RequestParam MultipartFile file, @PathVariable String email) throws IOException {
        return service.addImage(email,file);
    }

}

