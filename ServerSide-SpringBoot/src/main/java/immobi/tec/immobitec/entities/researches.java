package immobi.tec.immobitec.entities;

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
public class researches implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_research;

    private String searchPhrase ;

    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser user;
}
