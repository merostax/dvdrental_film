package entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode
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

}
