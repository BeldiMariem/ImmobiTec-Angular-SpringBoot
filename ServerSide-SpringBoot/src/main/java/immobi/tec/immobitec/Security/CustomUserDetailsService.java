package immobi.tec.immobitec.Security;



import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.repositories.AdresseRepository;
import immobi.tec.immobitec.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles=null;

            AppUser user = userRepository.findByEmail(email);
            if (user != null) {
                roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName()));
                return new User(user.getEmail(), user.getPassword(), roles);
            }
            throw new UsernameNotFoundException("User not found with the name " + email);
    }


}