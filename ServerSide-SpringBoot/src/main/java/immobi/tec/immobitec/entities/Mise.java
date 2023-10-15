package immobi.tec.immobitec.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Mise implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_mise;
    private float montant ;

    @ManyToOne(cascade = CascadeType.ALL) /**/
    @JsonIgnore
    private Auction auction;

    @ManyToOne(cascade = CascadeType.ALL) /**/
    private AppUser user;




}
