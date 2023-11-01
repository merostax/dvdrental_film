package dtos;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"id", "firstName", "lastName", "filmsHref"})
public class ActorDTO {
    private int id;
    private String firstName;
    private String lastName;
    @JsonbProperty("films")
    private String filmsHref;
}

