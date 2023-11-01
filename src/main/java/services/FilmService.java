package services;

import clienTargetRepository.StoreServiceClientProvider;
import dtos.ActorDTO;
import dtos.FilmDTO;
import entity.Actor;
import entity.Category;
import entity.Film;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositories.ActorRepository;
import repositories.CategoryRepository;
import repositories.FilmRepository;
import util.DTOEntityUtil;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Path("films")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class FilmService {
    @Inject
    StoreServiceClientProvider storeServiceClientProvider;
    @Inject
    private FilmRepository filmRepository;
    @Inject
    private ActorRepository actorRepository;
    @Inject
    private CategoryRepository categoryRepository;

    @GET
    public Response getFilms(@QueryParam("page") @DefaultValue("1") int page) {
        int pageSize = 20;
        List<Film> films = filmRepository.getFilms(page, pageSize);
        List<FilmDTO> filmDTOs = films.stream()
                .map(e->DTOEntityUtil.createFilmDTO(e))
                .collect(Collectors.toList());

        return Response.ok(filmDTOs).build();
    }

    @POST
    public Response createFilm(FilmDTO filmDTO) {
        Optional<Film> createdFilmOptional = Optional.ofNullable(filmRepository.createFilm(filmDTO));
        if (createdFilmOptional.isPresent()) {
            Film createdFilm = createdFilmOptional.get();
            return Response.status(Response.Status.CREATED)
                    .entity(createdFilm)
                    .build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create the film")
                    .build();
        }
    }
    @GET
    @Path("/{id}")
    public Response getFilm(@PathParam("id") int id) {
        Film film = filmRepository.getFilmById(id);

        if (film == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film not found")
                    .build();
        }

        FilmDTO filmDTO = DTOEntityUtil.createFilmDTO(film);

        return Response.ok(filmDTO).build();
    }
    @PUT
    @Path("/{filmId}/actors/{actorId}")
    public Response addActorToFilm(
            @PathParam("filmId") int filmId,
            @PathParam("actorId") int actorId
    ) {
        Film film = filmRepository.getFilmById(filmId);
        Actor actor = actorRepository.getActorById(actorId);

        if (film == null || actor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film or actor not found")
                    .build();
        }
        if (film.getActors().contains(actor)) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Actor is already associated with the film")
                    .build();
        }
        film.addActor(actor);
        filmRepository.updateFilm(film);

        return Response.status(Response.Status.CREATED)
                .header("Location", "/films/" + filmId + "/actors/" + actorId)
                .build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteFilm(@PathParam("id") int id) {
        Film film = filmRepository.getFilmById(id);

        if (film == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film not found")
                    .build();
        }
        WebTarget target = storeServiceClientProvider.getStoreServiceTarget()
                .path("rentals/film/" + id + "/check");
        Response checkStatusResponse = target.request().get();

        if (checkStatusResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            filmRepository.deleteFilm(film);
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("Film deleted")
                    .build();
        } else if(checkStatusResponse.getStatus() == Response.Status.FORBIDDEN.getStatusCode())
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Film has associated rentals and cannot be deleted")
                    .build();

        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity("Film has associated rentals and cannot be deleted")
                    .build();

    }
    @PATCH
    @Path("/{id}")
    public Response updateFilm(@PathParam("id") int id, FilmDTO updatedFilmDTO) {
        Film film = filmRepository.getFilmById(id);

        if (film == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film not found")
                    .build();
        }
        if (updatedFilmDTO.getTitle() != null) {
            film.setTitle(updatedFilmDTO.getTitle());
        }
        if (updatedFilmDTO.getDescription() != null) {
            film.setDescription(updatedFilmDTO.getDescription());
        }
        if (updatedFilmDTO.getRentalRate() != null) {
            film.setRentalRate(updatedFilmDTO.getRentalRate());
        }
        if (updatedFilmDTO.getReplacementCost() != null) {
            film.setReplacementCost(updatedFilmDTO.getReplacementCost());
        }
        filmRepository.updateFilm(film);

        return Response.status(Response.Status.NO_CONTENT)
                .entity("Film was updated.")
                .build();
    }
    @GET
    @Path("/{id}/actors")
    public Response getActorsOfFilm(@PathParam("id") int id) {
        Film film = filmRepository.getFilmById(id);

        if (film == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film not found")
                    .build();
        }
        Set<Actor> actors = film.getActors();
        return Response.ok(actors).build();
    }
    @GET
    @Path("/{id}/categories")
    public Response getCategoriesOfFilm(@PathParam("id") int filmId) {
        Film film = filmRepository.getFilmById(filmId);

        if (film == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film not found")
                    .build();
        }

        Set<Category> categories = film.getCategories();
        return Response.status(Response.Status.OK)
                .entity(categories)
                .build();
    }

    @PUT
    @Path("/{id}/categories/{category}")
    public Response addCategoryToFilm(
            @PathParam("id") int filmId,
            @PathParam("category") String categoryName
    ) {
        Film film = filmRepository.getFilmById(filmId);
        Category category = categoryRepository.getCategoryByName(categoryName);

        if (film == null || category == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Film or category not found")
                    .build();
        }
        film.addCategory(category);
        filmRepository.updateFilm(film);
        String location = "/films/" + filmId + "/categories/" +category.getName();
        return Response.status(Response.Status.CREATED)
                .header("Location", location)
                .entity("Category added to film.")
                .build();
    }

}
