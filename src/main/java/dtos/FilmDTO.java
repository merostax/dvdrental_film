package dtos;

import jakarta.json.bind.annotation.JsonbPropertyOrder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription( String description) {
        this.description = description;
    }

    public short getLength() {
        return this.length;
    }

    public void setLength( short length) {
        this.length = length;
    }

    public String getRating() {
        return this.rating;
    }

    public void setRating( String rating) {
        this.rating = rating;
    }

    public short getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear( short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public short getRentalDuration() {
        return this.rentalDuration;
    }

    public void setRentalDuration( short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return this.rentalRate;
    }

    public void setRentalRate( BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public BigDecimal getReplacementCost() {
        return this.replacementCost;
    }

    public void setReplacementCost( BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage( String language) {
        this.language = language;
    }

    public Map<String, String> getActors() {
        return this.actors;
    }

    public void setActors( Map<String, String> actors) {
        this.actors = actors;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories( List<String> categories) {
        this.categories = categories;
    }


}
