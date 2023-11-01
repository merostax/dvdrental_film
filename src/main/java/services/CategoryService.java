package services;

import entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import repositories.CategoryRepository;

import java.util.List;

@Path("categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class CategoryService {
    @Inject
    private CategoryRepository categoryRepository;

    @GET
    public List<String> getCategories() {
        List<Category> categories = categoryRepository.getCategories();
        List<String> categoryNames = convertCategoriesToStringList(categories);
        return categoryNames;
    }

    private List<String> convertCategoriesToStringList(List<Category> categories) {
        return categories.stream()
                .map(Category::getName)
                .toList();
    }
}
