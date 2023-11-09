package repositories;

import dtos.FilmDTO;
import entity.Category;
import entity.Language;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import entity.Film;
import jakarta.transaction.Transactional;
import util.DTOEntityUtil;
import util.EntityManagerProvider;

import java.util.List;

@ApplicationScoped
public class FilmRepository {
    @Inject
    private EntityManagerProvider entityManagerProvider;
    @Inject
    private LanguageRepository languageRepository;

    public List<Film> getFilms(int page, int pageSize) {
        EntityManager em = entityManagerProvider.getEntityManager();
        Query query = em.createQuery("SELECT f FROM Film f", Film.class);
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Transactional
    public Film getFilmById(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.find(Film.class, id);
    }
    @Transactional
    public long getFilmCount() {
        EntityManager em = entityManagerProvider.getEntityManager();
        Query query = em.createQuery("SELECT COUNT(f) FROM Film f");
        return (long) query.getSingleResult();
    }

    @Transactional
    public Film updateFilm(Film film) {
        EntityManager em = entityManagerProvider.getEntityManager();
        Film updatedFilm = em.merge(film);
        return updatedFilm;
    }
    @Transactional
    public Film createFilm(FilmDTO filmDTO) {
        EntityManager em = entityManagerProvider.getEntityManager();
        Film film = DTOEntityUtil.createFilmFromDTO(filmDTO);
        Language language = languageRepository.getLanguageByName(filmDTO.getLanguage());
        if (language != null) {
            film.setLanguage(language);
        } else return null;

        em.persist(film);
        return film;
    }
    @Transactional
    public void deleteFilm(Film film) {
        EntityManager em = entityManagerProvider.getEntityManager();
        em.remove(film);
    }
}
