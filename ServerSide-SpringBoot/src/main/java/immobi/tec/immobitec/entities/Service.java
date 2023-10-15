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
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_service;

    private String title ;
    private String description ;
    private float price ;
    @Lob
    private String Picture ;

    private int isLiked;
    private int isDisliked;

    @ElementCollection
    private List<Integer> likes = new ArrayList<>();

    @ElementCollection
    private List<Integer> Dislikes = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private AppUser user;

    @OneToMany(mappedBy = "service",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Appointement> appointements;






}
