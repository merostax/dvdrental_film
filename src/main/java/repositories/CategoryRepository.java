package repositories;

import entity.Film;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import entity.Category;

import java.util.List;

@ApplicationScoped
public class CategoryRepository {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public List<Category> getCategories() {
        Query query = em.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    @Transactional
    public Category getCategoryById(int categoryId) {
        return em.find(Category.class, categoryId);
    }

    @Transactional
    public Category getCategoryByName(String categoryName) {
        Query query = em.createQuery("SELECT c FROM Category c WHERE LOWER(c.name) = :categoryName", Category.class);
        query.setParameter("categoryName", categoryName.toLowerCase());
        List<Category> categories = query.getResultList();

        if (categories.isEmpty()) {
            return null;
        } else {
            return categories.get(0);
        }
    }
}
