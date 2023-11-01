import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import services.ActorService;
import services.CategoryService;
import services.FilmService;
import services.LanguageService;

import java.util.ArrayList;
import java.util.List;

@Path("/")
public class RootResource {

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEndpoints() {
        List<String> endpoints = new ArrayList<>();

        if (ActorService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = ActorService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }

        if (CategoryService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = CategoryService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }

        if (FilmService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = FilmService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }
        if (LanguageService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = LanguageService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }
        Jsonb jsonb = JsonbBuilder.create();
        String jsonEndpoints = jsonb.toJson(endpoints);

        return Response.ok(jsonEndpoints).build();
    }
}
