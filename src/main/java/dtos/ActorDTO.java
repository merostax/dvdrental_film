package dtos;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.util.Map;


@JsonbPropertyOrder({"id", "firstName", "lastName", "filmsHref"})
public class ActorDTO {
    private int id;
    private String firstName;
    private String lastName;
    @JsonbProperty("films")
    private Map<String,String> filmsHref;
    public ActorDTO() {
    }  

    public ActorDTO( int id,  String firstName,  String lastName,  Map<String, String> filmsHref) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmsHref = filmsHref;
    }


    public int getId() {
        return this.id;
    }

    public void setId( int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName( String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName( String lastName) {
        this.lastName = lastName;
    }

    public Map<String, String> getFilmsHref() {
        return this.filmsHref;
    }

    public void setFilmsHref( Map<String, String> filmsHref) {
        this.filmsHref = filmsHref;
    }


}

