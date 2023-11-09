package entity;

import dtos.FilmDTO;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude ="films" )
@NoArgsConstructor
public class Actor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_id", nullable = false)
    private int actorId;

    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = new Timestamp(System.currentTimeMillis());
    }
    @ManyToMany(mappedBy = "actors", fetch = FetchType.EAGER)
    @JsonbTransient
    private List<Film> films =new ArrayList<>();

}
