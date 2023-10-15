package immobi.tec.immobitec.DTO;

import immobi.tec.immobitec.entities.Adresse;
import immobi.tec.immobitec.entities.TypeBadge;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id_user;
    private String name ;
    private String lastname ;
    private String password ;
    private String email ;
    private Date birthday ;
    private int phoneNumber ;
    private float wallet ;
    private String Picture ;
    private String verificationCode;
    private boolean enabled;
    private String resetPasswordToken;
    private String secret;
    private boolean banned;
    private TypeBadge badge;
    private int role_id;
    private String role_name;
    private Adresse adresse;
}
