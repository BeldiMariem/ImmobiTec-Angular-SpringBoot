package immobi.tec.immobitec.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.Instant;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Transactional
@Table(name = "User")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    private String name ;
    private String lastname ;
    private String password ;
    private String email ;
    @Temporal(TemporalType.DATE)
    private Date birthday ;
    private int phoneNumber ;
    private float wallet ;
    @Lob
    private String Picture ;
    @Enumerated(EnumType.STRING)
    private TypeBadge badge=TypeBadge.DEBUTANT;
    private int score=0;
    private boolean blocked=false;
    @Column(name = "verification_code", length = 64)
    private String verificationCode;
    private boolean enabled;
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    private String secret;

    private boolean banned;
    private Instant banExpiration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Auction> auctionsParticipation;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Auction> auctionscreator;


    @ManyToOne(cascade = CascadeType.ALL)
    private Rolee role;

    @ManyToOne(cascade = CascadeType.ALL)
    private Adresse adresse;



    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<DormRoom> dormRoomsOwner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    Set<DormRoom> dormRoomsCustomer;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Property> properties;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Product> products;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Ordre> orders;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Service> services;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Appointement> appointements;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Comment> comments;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Post> posts;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<PostVote> postVotes;

    @OneToOne(mappedBy = "appUser",cascade = CascadeType.ALL)
    @JsonIgnore
    private Reservation reservation;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    Set<DormRoom> dormRooms;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Mise> mises;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<researches> researches;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Rating> ratings;


    @OneToMany(mappedBy = "appUser",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Feedback> feedbacks;







}
