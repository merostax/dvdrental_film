package entity;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "category_id", nullable = false)
    private int categoryId;

    @Basic
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Basic
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;
    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @JsonbTransient
    private Set<Film> films =new HashSet<>();

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId( int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return this.name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate( Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Set<Film> getFilms() {
        return this.films;
    }

    public void setFilms( Set<Film> films) {
        this.films = films;
    }
}
