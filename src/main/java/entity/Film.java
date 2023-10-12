package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Film {
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
    @JoinColumn(name = "language_id", referencedColumnName = "language_id")
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

}
