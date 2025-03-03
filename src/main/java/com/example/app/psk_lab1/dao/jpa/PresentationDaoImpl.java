package com.example.app.psk_lab1.dao.jpa;

import com.example.app.psk_lab1.entity.Presentation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class PresentationDaoImpl implements PresentationDao {

    @PersistenceContext(name = "primary")
    private EntityManager em;

    @Override
    @Transactional
    public List<Presentation> findAll() {
        return em.createQuery("SELECT p FROM Presentation p", Presentation.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Presentation findById(Long id) {
        return em.find(Presentation.class, id);
    }

    @Override
    @Transactional
    public void create(Presentation presentation) {
        em.persist(presentation);
    }

    @Override
    @Transactional
    public Presentation update(Presentation presentation) {
        return em.merge(presentation);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Presentation p = em.find(Presentation.class, id);
        if (p != null) {
            em.remove(p);
        }
    }

    @Override
    @Transactional
    public List<Presentation> findByConferenceId(Long conferenceId) {
        return em.createQuery(
                        "SELECT p FROM Presentation p WHERE p.conference.id = :conferenceId",
                        Presentation.class)
                .setParameter("conferenceId", conferenceId)
                .getResultList();
    }
}
