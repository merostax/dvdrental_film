package repositories;

import dtos.ActorDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import entity.Actor;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ActorRepository {

    @PersistenceContext
    EntityManager em;


    @Transactional
    public Actor createActor(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setFirstName(actorDTO.getFirstName());
        actor.setLastName(actorDTO.getLastName());
        em.persist(actor);
      return actor;
    }

    @Transactional
    public Actor getActorById(int id) {
        return em.find(Actor.class, id);
    }

    @Transactional
    public Actor updateActor(ActorDTO actorDTO,Actor actor) {
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
        Actor actor = getActorById(id);
        if (actor != null) {
            em.remove(actor);
        }
    }

    @Transactional
    public long getActorCount() {
        Query query = em.createQuery("SELECT COUNT(a) FROM Actor a");
        return (long) query.getSingleResult();
    }

    @Transactional
    public List<Actor> getActors(int page, int pageSize) {
        Query query = em.createQuery("SELECT a FROM Actor a", Actor.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
