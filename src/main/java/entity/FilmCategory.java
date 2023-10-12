package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "film_category", schema = "public", catalog = "film-db")
@IdClass(entity.FilmCategoryPK.class)
@Getter
@Setter
@EqualsAndHashCode
public class FilmCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id", nullable = false)
    private int filmId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

}
