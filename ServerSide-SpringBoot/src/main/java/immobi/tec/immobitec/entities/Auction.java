package immobi.tec.immobitec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Auction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_auction;

    private String description ;
    private float entryFee ;

    @Temporal(TemporalType.DATE)
    private Date startDate ;

    // winner
    private String winner;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToMany(mappedBy = "auctionsParticipation")
    @JsonIgnore
    Set<AppUser> users;

    @ManyToOne()
    @JsonIgnore
    private AppUser user;

    @OneToMany(mappedBy = "auction",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    Set<Mise> mises;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;


}
