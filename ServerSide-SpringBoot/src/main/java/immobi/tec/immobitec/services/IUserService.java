package immobi.tec.immobitec.services;

import immobi.tec.immobitec.DTO.UserDTO;
import immobi.tec.immobitec.entities.AppUser;

import immobi.tec.immobitec.entities.Service;

import immobi.tec.immobitec.entities.Rolee;
import immobi.tec.immobitec.entities.UserPage;
import immobi.tec.immobitec.entities.UserSearchCriteria;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public interface IUserService {

 //   public AppUser getUserById(int id);
    public List<AppUser> getAllUsers();
    public AppUser addUser(AppUser user);
    public AppUser updateUser(AppUser user);

    public void updateScore(AppUser user,String action);

    public Page<AppUser> getUsers(UserPage userPage,
                                  UserSearchCriteria userSearchCriteria);
    public UserDTO getUserById(int id);
    public AppUser addUser(UserDTO userDTO);
    public boolean checkIfUserExist(String email);
    public AppUser update(UserDTO userDTO) ;
    public AppUser register(UserDTO userDTO, String siteURL)  throws  UnsupportedEncodingException, MessagingException,Exception ;
    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException;

     void sendVerificationEmail(AppUser user, String siteURL) throws MessagingException, UnsupportedEncodingException ;
    public boolean verify(String verificationCode);
    public void updateResetPasswordToken(AppUser user) ;
    public void updateResetPasswordToken(String token, String email) ;
    public AppUser getByResetPasswordToken(String token);
    public void updatePassword(AppUser customer, String newPassword);


    public void banUser(String email, int banDurationMinutes);

     void scheduleBanLift(int userId, Instant banExpiration);

}
