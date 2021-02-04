package com.inflearn.springdatajpa.연습용.domain.locker;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class LockerRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Locker locker) {
        em.persist(locker);
        return locker.getId();
    }

    public Locker find(Long id) {
        return em.find(Locker.class, id);
    }

    public Locker findById(Long id) {
        return em.createQuery("select l from Locker l where l.id = :id", Locker.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Locker> findAll() {
        return em.createQuery("select l from Locker l", Locker.class)
                .getResultList();
    }
}
