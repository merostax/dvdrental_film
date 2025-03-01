package services;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import dtos.FilmActorDto;
import entity.Film;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import entity.Actor;
import repositories.ActorRepository;
import dtos.ActorDTO;
import util.DTOEntityUtil;
import util.Hrefs;

@Path("actors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ActorService {
    @Inject
    private ActorRepository actorRepository;
    @Context
    UriInfo uriInfo ;

    @Inject
    private Hrefs hrefs;
     @Inject DTOEntityUtil DTOEntityUtil;

    @GET
    public Response getActors(@Valid @QueryParam("page") @DefaultValue("1") @Min(1) int page) {
        int pageSize = 10;
        List<Actor> actors = actorRepository.getActors(page, pageSize);

        List<ActorDTO> actorDTOs = actors.stream()
                .map(this::createActorDTOWithFilmHrefs)
                .collect(Collectors.toList());
        return Response.ok(actorDTOs).build();
    }
    private ActorDTO createActorDTOWithFilmHrefs(Actor actor) {
        return DTOEntityUtil.createActorDTOWithFilmHrefs(actor);
    }
    @POST
    public Response createActor(ActorDTO actorDTO) {
        Actor createdActor = actorRepository.createActor(actorDTO);
        URI actorUri = uriInfo.getAbsolutePathBuilder()
                .path(Integer.toString(createdActor.getActorId()))
                .build();
        return Response.status(Response.Status.CREATED)
                .entity("Actor was created.")
                .location(actorUri)
                .build();
    }

    @GET
    @Path("/count")
    public long getActorCount() {
        return actorRepository.getActorCount();
    }

    @GET
    @Path("/{id}")
    public Response getActor(@PathParam("id") int id) {
        Optional<Actor> actorOptional = Optional.ofNullable(actorRepository.getActorById(id));

        if (actorOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Actor not found")
                    .build();
        }
        Map filmsHref = getFilmsHref(actorOptional.get());
        Actor actor = actorOptional.get();
        ActorDTO actorDTO = new ActorDTO(actor.getActorId(), actor.getFirstName(), actor.getLastName(), filmsHref);
        return Response.ok(actorDTO).build();
    }
    private Map<String, String> getFilmsHref(Actor actor) {
        Map<String, String> filmHref = new HashMap<>();
        filmHref.put("href", hrefs.getFilmHref()!=null?hrefs.getFilmHref() + "actors/" + actor.getActorId() + "/films":"");
        return filmHref;
    }


    @DELETE
    @Path("/{id}")
    public Response deleteActor(@PathParam("id") int id) {
        Optional<Actor> actorOptional = Optional.ofNullable(actorRepository.getActorById(id));

        if (actorOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Actor not found")
                    .build();
        }

     if(!actorRepository.getActorById(id).getFilms().isEmpty()) {
         return Response.status(Response.Status.FORBIDDEN)
             .entity("Actor has associated films and cannot be deleted")
             .build();
     }

        actorRepository.deleteActor(id);
        return Response.status(Response.Status.NO_CONTENT)
                .entity("Actor deleted")
                .build();
    }

    @PATCH
    @Path("/{id}")
    public Response updateActor(@PathParam("id") int id, ActorDTO actorDTO) {
        Optional<Actor> actorOptional = Optional.ofNullable(actorRepository.getActorById(id));

        if (actorOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Actor not found")
                    .build();
        }

        actorRepository.updateActor(actorDTO,actorRepository.getActorById(id));
        return Response.status(Response.Status.NO_CONTENT)
                .entity("Actor was updated.")
                .build();
    }
    @GET
    @Path("/{id}/films")
    public List<FilmActorDto> getFilmsForActor(@PathParam("id") int id) {
        Optional<Actor> actorOptional = Optional.ofNullable(actorRepository.getActorById(id));
        if (actorOptional.isEmpty()) {
            throw new NotFoundException("Actor not found");
        }
        List<Film> filmList = actorOptional.get().getFilms();

        List<FilmActorDto> filmDTOList = filmList.stream()
                .map(DTOEntityUtil::createFilmActorDto)
                .collect(Collectors.toList());

        return filmDTOList;
    }
}
