package com.example.app.psk_lab1.dao.jpa;

import com.example.app.psk_lab1.entity.Conference;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ConferenceDaoImpl implements ConferenceDao {

    @PersistenceContext(name = "primary")
    private EntityManager em;

    @Override
    @Transactional
    public List<Conference> findAll() {
        return em.createQuery("SELECT c FROM Conference c", Conference.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Conference findById(Long id) {
        return em.find(Conference.class, id);
    }

    @Override
    @Transactional
    public void create(Conference conference) {
        em.persist(conference);
    }

    @Override
    @Transactional
    public Conference update(Conference conference) {
        return em.merge(conference);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Conference c = em.find(Conference.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}
