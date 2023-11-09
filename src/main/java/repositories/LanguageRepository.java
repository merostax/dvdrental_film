package repositories;

import entity.Language;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import util.EntityManagerProvider;

import java.util.List;

@ApplicationScoped
public class LanguageRepository {
    @Inject
    EntityManagerProvider entityManagerProvider;

    @Transactional
    public Language getLanguageById(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.find(Language.class, id);
    }
    @Transactional
    public Language getLanguageByName(String name) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.createQuery("SELECT l FROM Language l WHERE LOWER(l.name) = :name", Language.class)
                .setParameter("name", name.toLowerCase())
                .getSingleResult();
    }

    @Transactional
    public List<Language> getAllLanguages() {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.createQuery("SELECT l FROM Language l", Language.class)
                .getResultList();
    }

}
