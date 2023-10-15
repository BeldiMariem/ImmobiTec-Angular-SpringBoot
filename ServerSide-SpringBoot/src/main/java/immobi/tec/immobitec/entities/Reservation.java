package immobi.tec.immobitec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reservation;
    private int number_nights;
    @Temporal(TemporalType.DATE)
    private Date date_reservation ;

    @Temporal(TemporalType.DATE)
    private Date Checkindate ;

    @Temporal(TemporalType.DATE)
    private Date Checkoutdate ;





    @ManyToOne(cascade = CascadeType.ALL)
    private DormRoom dormRoom;

    @OneToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
}
