package com.inflearn.springdatajpa.training.domain.member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberTestRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(MemberTest member) {
        em.persist(member);
        return member.getId();
    }

    public MemberTest find(Long id) {
        return em.find(MemberTest.class, id);
    }

    public MemberTest findById(Long id) {
        return em.createQuery("select m from MemberTest m where m.id=:id", MemberTest.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
