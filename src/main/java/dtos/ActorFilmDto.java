package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

@JsonbPropertyOrder({"firstname_Lastname", "href"})
public class ActorFilmDto {
    private String firstname_Lastname;
    private String href;

    public String getFirstname_Lastname() {
        return this.firstname_Lastname;
    }

    public void setFirstname_Lastname( String firstname_Lastname) {
        this.firstname_Lastname = firstname_Lastname;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(final String href) {
        this.href = href;
    }
}
