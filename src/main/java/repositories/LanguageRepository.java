package repositories;

import entity.Language;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class LanguageRepository {

    @PersistenceContext
    EntityManager em;


    @Transactional
    public Language getLanguageById(int id) {
        return em.find(Language.class, id);
    }
    @Transactional
    public Language getLanguageByName(String name) {
        return em.createQuery("SELECT l FROM Language l WHERE LOWER(l.name) = :name", Language.class)
                .setParameter("name", name.toLowerCase())
                .getSingleResult();
    }

    @Transactional
    public List<Language> getAllLanguages() {
        return em.createQuery("SELECT l FROM Language l", Language.class)
                .getResultList();
    }

}
