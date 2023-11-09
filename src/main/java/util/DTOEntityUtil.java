package util;

import dtos.ActorDTO;
import dtos.ActorFilmDto;
import dtos.FilmActorDto;
import dtos.FilmDTO;
import entity.Actor;
import entity.Category;
import entity.Film;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DTOEntityUtil {

    public static ActorDTO createActorDTOWithFilmHrefs(Actor actor) {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setId(actor.getActorId());
        actorDTO.setFirstName(actor.getFirstName());
        actorDTO.setLastName(actor.getLastName());
        Map<String, String> filmHref = new HashMap<>();
        filmHref.put("href", Hrefs.FILM.getHref() + "actors/" + actor.getActorId() + "/films");
        actorDTO.setFilmsHref(filmHref);
        return actorDTO;
    }

    public static Film createFilmFromDTO(FilmDTO filmDTO) {
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
    public static FilmDTO createFilmDTO(Film film) {
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
        actors.put("href",Hrefs.FILM.getHref()+"films/" + film.getFilmId()+"/actors/");
        filmDTO.setActors(actors);
        filmDTO.setCategories(film.getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toList()));
        return filmDTO;
    }
    public static FilmActorDto createFilmActorDto(Film film) {
        FilmActorDto filmDTO = new FilmActorDto();
        filmDTO.setTitle(film.getTitle());
        filmDTO.setHref(Hrefs.FILM.getHref()+"films/"+film.getFilmId());
        return filmDTO;
    }
    public static ActorFilmDto  createActorFilmDto(Actor actor) {
        ActorFilmDto actorFilmDto = new ActorFilmDto();
        actorFilmDto.setFirstname_Lastname(actor.getFirstName()+" "+actor.getLastName());
        actorFilmDto.setHref(Hrefs.FILM.getHref()+"actors/"+actor.getActorId());
        return actorFilmDto;
    }





}
