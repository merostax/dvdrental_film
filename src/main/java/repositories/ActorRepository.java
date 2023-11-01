package repositories;

import dtos.ActorDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import entity.Actor;
import util.EntityManagerProvider;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ActorRepository {
    @Inject
    private EntityManagerProvider entityManagerProvider;


    @Transactional
    public Actor createActor(ActorDTO actorDTO) {
        EntityManager em = entityManagerProvider.getEntityManager();
        Actor actor = new Actor();
        actor.setFirstName(actorDTO.getFirstName());
        actor.setLastName(actorDTO.getLastName());
        em.persist(actor);
      return actor;
    }

    @Transactional
    public Actor getActorById(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.find(Actor.class, id);
    }

    @Transactional
    public Actor updateActor(ActorDTO actorDTO,Actor actor) {
        EntityManager em = entityManagerProvider.getEntityManager();
        if (actorDTO.getFirstName() != null && !actorDTO.getFirstName().isEmpty()) {
            actor.setFirstName(actorDTO.getFirstName());
        }

        if (actorDTO.getLastName() != null && !actorDTO.getLastName().isEmpty()) {
            actor.setLastName(actorDTO.getLastName());
        }

        Actor updated = em.merge(actor);
        return updated;
    }

    @Transactional
    public void deleteActor(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        Actor actor = getActorById(id);
        if (actor != null) {
            em.remove(actor);
        }
    }

    @Transactional
    public long getActorCount() {
        EntityManager em = entityManagerProvider.getEntityManager();
        Query query = em.createQuery("SELECT COUNT(a) FROM Actor a");
        return (long) query.getSingleResult();
    }

    @Transactional
    public List<Actor> getActors(int page, int pageSize) {
        EntityManager em = entityManagerProvider.getEntityManager();
        Query query = em.createQuery("SELECT a FROM Actor a", Actor.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
