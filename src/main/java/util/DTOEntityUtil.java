package util;

import dtos.ActorDTO;
import dtos.ActorFilmDto;
import dtos.FilmActorDto;
import dtos.FilmDTO;
import entity.Actor;
import entity.Category;
import entity.Film;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
@ApplicationScoped
public class DTOEntityUtil {
   @Inject Hrefs hrefs;

    public  ActorDTO createActorDTOWithFilmHrefs(Actor actor) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(actor.getActorId());
        actorDTO.setFirstName(actor.getFirstName());
        actorDTO.setLastName(actor.getLastName());
        Map<String, String> filmHref = new HashMap<>();
        filmHref.put("href", hrefs.getFilmHref()!=null?hrefs.getFilmHref() + "actors/" + actor.getActorId() + "/films":"");
        actorDTO.setFilmsHref(filmHref);
        return actorDTO;
    }

    public  Film createFilmFromDTO(FilmDTO filmDTO) {
        Film film = new Film();
        film.setTitle(filmDTO.getTitle());
        film.setDescription(filmDTO.getDescription());
        film.setLength(filmDTO.getLength());
        film.setRating(filmDTO.getRating());
        film.setReleaseYear(filmDTO.getReleaseYear());
        film.setRentalDuration(filmDTO.getRentalDuration());
        film.setRentalRate(filmDTO.getRentalRate());
        film.setReplacementCost(filmDTO.getReplacementCost());
        return film;
    }
    public  FilmDTO createFilmDTO(Film film) {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setId(film.getFilmId());
        filmDTO.setTitle(film.getTitle());
        filmDTO.setDescription(film.getDescription());
        filmDTO.setLength(film.getLength());
        filmDTO.setRating(film.getRating());
        filmDTO.setReleaseYear(film.getReleaseYear());
        filmDTO.setRentalDuration(film.getRentalDuration());
        filmDTO.setRentalRate(film.getRentalRate());
        filmDTO.setReplacementCost(film.getReplacementCost());
        filmDTO.setLanguage(film.getLanguage().getName());
        Map<String, String> actors = new HashMap<>();
        actors.put("href",hrefs.getFilmHref()!=null?hrefs.getFilmHref()+"films/" + film.getFilmId()+"/actors/":"");
        filmDTO.setActors(actors);
        filmDTO.setCategories(film.getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toList()));
        return filmDTO;
    }
    public  FilmActorDto createFilmActorDto(Film film) {
        FilmActorDto filmDTO = new FilmActorDto();
        filmDTO.setTitle(film.getTitle());
        filmDTO.setHref(hrefs.getFilmHref()!=null?hrefs.getFilmHref()+"films/"+film.getFilmId():"");
        return filmDTO;
    }
    public  ActorFilmDto  createActorFilmDto(Actor actor) {
        ActorFilmDto actorFilmDto = new ActorFilmDto();
        actorFilmDto.setFirstname_Lastname(actor.getFirstName()+" "+actor.getLastName());
        actorFilmDto.setHref(hrefs.getFilmHref()!=null?hrefs.getFilmHref()+"actors/"+actor.getActorId():"");
        return actorFilmDto;
    }





}
