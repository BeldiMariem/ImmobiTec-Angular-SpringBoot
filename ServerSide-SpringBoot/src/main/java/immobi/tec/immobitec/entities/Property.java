package immobi.tec.immobitec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_property;

    private String description ;
    private Boolean closed ;

    private Double x ;
    private Double y ;
    @Enumerated(EnumType.STRING)
    private TypeProperty type ;


    @ManyToOne()
    private AppUser user;

    @OneToOne(cascade = CascadeType.ALL)
@JsonIgnore
    private Announcement announcement;

    @OneToOne(cascade = CascadeType.ALL)
    private Adresse adresse;

    @OneToMany(mappedBy = "property")
    Set<Appointement> appointements;

    @OneToMany(mappedBy = "property")
    Set<ImageProperty> images;
    public void setAnnouncement(Announcement announcement) {
        if (announcement != null) {
            this.announcement = announcement;
            announcement.setProperty(this);
        }
    }
}
