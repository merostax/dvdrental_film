package entity;

import java.io.Serializable;
import java.util.Objects;

public class FilmActorPK implements Serializable {
    private int actorId;
    private int filmId;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        final FilmActorPK that = (FilmActorPK) o;
        return this.actorId == that.actorId && this.filmId == that.filmId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.actorId, this.filmId);
    }
}
