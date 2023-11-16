package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "film_category", schema = "public", catalog = "film-db")
@IdClass(entity.FilmCategoryPK.class)
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

    public int getFilmId() {
        return this.filmId;
    }

    public void setFilmId(final int filmId) {
        this.filmId = filmId;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(final int categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(final Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
