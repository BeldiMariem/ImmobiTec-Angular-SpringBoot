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
public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_feedback;
    private String feedback_message;
    private Integer rating;
    private String title;
    private Integer boosts;
    private Boolean boosted = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private DormRoom dormRoom;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private AppUser appUser;

    public Integer getBoosts() {
        return boosts;
    }

    public void setBoosts(Integer boosts) {
        this.boosts = boosts;
    }

    public Boolean getBoosted() {
        return boosted;
    }

    public void setBoosted(Boolean boosted) {
        this.boosted = boosted;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id_feedback=" + id_feedback +
                ", feedback_message='" + feedback_message + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public int getId() {
        return id_feedback;
    }

    public void setId(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return feedback_message;
    }

    public void setContent(String content) {
        this.feedback_message = feedback_message;
    }

    public DormRoom getDormRoom() {
        return dormRoom ;
    }

    public void setDormRoom(DormRoom dormRoom) {
        this.dormRoom = dormRoom;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}



