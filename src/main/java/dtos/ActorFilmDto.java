package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"firstname_Lastname", "href"})
public class ActorFilmDto {
    private String firstname_Lastname;
    private String href;
}
