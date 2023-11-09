package dtos;

import entity.Category;
import entity.Film;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"id", "title", "description", "length", "rating", "releaseYear", "rentalDuration", "rentalRate", "replacementCost", "language", "actors", "categories"})
public class FilmDTO {
    private long id;
    private String title;
    private String description;
    private short length;
    private String rating;
    private short releaseYear;
    private short rentalDuration;
    private BigDecimal rentalRate;
    private BigDecimal replacementCost;
    private String language;
    private Map<String, String> actors;
    private List<String> categories;
}
