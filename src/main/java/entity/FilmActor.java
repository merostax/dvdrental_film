package entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "film_actor", schema = "public", catalog = "film-db")
@IdClass(entity.FilmActorPK.class)
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

    public int getActorId() {
        return this.actorId;
    }

    public void setActorId( int actorId) {
        this.actorId = actorId;
    }

    public int getFilmId() {
        return this.filmId;
    }

    public void setFilmId( int filmId) {
        this.filmId = filmId;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate( Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
