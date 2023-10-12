package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Entity
@Table(name = "film_actor", schema = "public", catalog = "film-db")
@IdClass(entity.FilmActorPK.class)
@EqualsAndHashCode
public class FilmActor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_id", nullable = false)
    private int actorId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id", nullable = false)
    private int filmId;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

}
