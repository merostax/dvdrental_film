package util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProvider {

    @PersistenceContext(unitName = "FilmDB")
    private EntityManager em;

    @PostConstruct
    public void init() {
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @PreDestroy
    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
}
