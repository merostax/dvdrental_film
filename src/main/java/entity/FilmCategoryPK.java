package entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode


public class FilmCategoryPK implements Serializable {
    private int filmId;
    private int categoryId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmCategoryPK that = (FilmCategoryPK) o;

        if (filmId != that.filmId) return false;
        return categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        int result = filmId;
        result = 31 * result + categoryId;
        return result;
    }
}
