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
public class Ordre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;
    private float total_price ;
    @Temporal(TemporalType.DATE)
    private Date order_date ;


    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Card> cards;
    @ManyToOne()
    private AppUser user;
    @ManyToOne()
    @JsonIgnore
    Product product;

}
