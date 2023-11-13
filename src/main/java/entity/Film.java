package entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"actors", "categories"})
public class Film implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id", nullable = false)
    private int filmId;


    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;


    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;


    @Basic
    @Column(name = "release_year", nullable = true)
    private Short releaseYear;


    @ManyToOne
    @JoinColumn(name = "language_id")
    @JsonbTransient
    private Language language;

    @Basic
    @Column(name = "rental_duration", nullable = false)
    private short rentalDuration;


    @Basic
    @Column(name = "rental_rate", nullable = false, precision = 2)
    private BigDecimal rentalRate;


    @Basic
    @Column(name = "length", nullable = true)
    private Short length;


    @Basic
    @Column(name = "replacement_cost", nullable = false, precision = 2)
    private BigDecimal replacementCost;


    @Basic
    @Column(name = "rating", nullable = true, length = 5)
    private String rating;


    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @PrePersist
    @PreUpdate
    public void onUpdate() {
        this.lastUpdate = new Timestamp(System.currentTimeMillis());
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @JsonbTransient
    private List<Actor> actors =new ArrayList<>();


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonbTransient
    private Set<Category> categories = new HashSet<>();
    public void addCategory(Category category) {
        categories.add(category);
    }
    public void addActor(Actor actor){
        actors.add(actor);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;

        return filmId == film.filmId;
    }

    @Override
    public int hashCode() {
        return filmId;
    }
}
