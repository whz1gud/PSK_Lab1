package com.example.app.psk_lab1.dao.jpa;

import com.example.app.psk_lab1.entity.Presentation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class PresentationDaoImpl implements PresentationDao {

    @PersistenceContext(name = "primary")
    private EntityManager em;

    @Override
    public List<Presentation> findAll() {
        return em.createQuery("SELECT p FROM Presentation p", Presentation.class)
                .getResultList();
    }

    @Override
    public Presentation findById(Long id) {
        return em.find(Presentation.class, id);
    }

    @Override
    public void create(Presentation presentation) {
        em.persist(presentation);
    }

    @Override
    public Presentation update(Presentation presentation) {
        return em.merge(presentation);
    }

    @Override
    public void delete(Long id) {
        Presentation p = em.find(Presentation.class, id);
        if (p != null) {
            em.remove(p);
        }
    }

    @Override
    public Presentation findByConferenceId(Long conferenceId) {
        return em.find(Presentation.class, conferenceId);
    }
}
