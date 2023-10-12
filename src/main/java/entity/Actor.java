package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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

}
