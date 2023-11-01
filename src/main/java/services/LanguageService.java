package services;

import entity.Language;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repositories.LanguageRepository;

import java.util.List;

@Path("languages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LanguageService {

    @Inject
    private LanguageRepository languageRepository;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getLanguagesAsText() {
        List<Language> languages = languageRepository.getAllLanguages();
        if (languages != null && !languages.isEmpty()) {
            StringBuilder text = new StringBuilder();
            for (Language language : languages) {
                text.append(language.getName()).append("\n");
            }
            return Response.ok(text.toString()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No languages found")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getLanguage(@PathParam("id") int id) {
        Language language = languageRepository.getLanguageById(id);
        if (language != null) {
            return Response.ok(language).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Language not found")
                    .build();
        }
    }

}

