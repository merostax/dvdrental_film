package entity;


import java.io.Serializable;

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
}
