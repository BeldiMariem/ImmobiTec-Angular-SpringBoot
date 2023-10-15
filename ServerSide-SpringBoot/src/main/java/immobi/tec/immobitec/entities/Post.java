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
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_post;

    private String description ;
    private int likes ;
    private int dislikes ;
    @Temporal(TemporalType.DATE)
    private Date date_post ;
    private String picture ;
    private Boolean boosted=false;
    private String owner;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private AppUser user;
    // @JsonIgnore

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Forum forum;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @ToString.Exclude
    Set<Comment> comments;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    @ToString.Exclude
    Set<PostVote> postVotes;

}
