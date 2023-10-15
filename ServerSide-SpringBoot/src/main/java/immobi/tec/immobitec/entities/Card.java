package immobi.tec.immobitec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_card;

    private int quantity_ordered ;


    @ManyToMany(mappedBy = "cards",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Product> products;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Ordre order;


}
