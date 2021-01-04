package com.inflearn.springdatajpa.training.domain.membertest2;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberTest2Repository2 {

    @PersistenceContext
    private EntityManager em;

    public MemberTest2 findById(Long id) {
        return em.createQuery("select m from MemberTest2 m where m.id=:id", MemberTest2.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<MemberTest2> findAll() {
        return em.createQuery("select m from MemberTest2 m", MemberTest2.class)
                .getResultList();
    }
}
