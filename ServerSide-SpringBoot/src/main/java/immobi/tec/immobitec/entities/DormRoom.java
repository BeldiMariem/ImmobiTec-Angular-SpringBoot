package immobi.tec.immobitec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DormRoom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Room;

    private String name ;
    @Lob
    private String Picture ;
    private int nbPlace;
    private String description;
    @Enumerated(EnumType.STRING)
    private Availability status;
    private int isLiked;
    private int isDisliked;
    @Enumerated(EnumType.STRING)
    private DormRoomType dormRoomType;
    private int price;



    @ElementCollection
    private List<Integer> likes = new ArrayList<>();

    @ElementCollection
    private List<Integer> Dislikes = new ArrayList<>();






    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private AppUser user;


    @ManyToMany(mappedBy = "dormRoomsCustomer",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<AppUser> users;


    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Adresse adresse;

    @OneToMany(mappedBy = "dormRoom",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Reservation> reservations;

    @OneToMany(mappedBy = "dormRoom",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Feedback> feedbacks;

}
