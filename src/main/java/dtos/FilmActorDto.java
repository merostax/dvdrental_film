package dtos;
import jakarta.json.bind.annotation.JsonbPropertyOrder;


@JsonbPropertyOrder({"title", "filmsHref"})
public class FilmActorDto {
    private String title;
    private String href;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref( String href) {
        this.href = href;
    }
}
