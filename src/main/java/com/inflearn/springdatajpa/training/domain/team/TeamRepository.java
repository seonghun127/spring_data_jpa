package com.inflearn.springdatajpa.training.domain.team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Team team) {
        em.persist(team);
        return team.getId();
    }

    public Team find(Long id) {
        return em.find(Team.class, id);
    }

    public Team findWithJpql(Long id) {
        return em.createQuery("select t from Team t where t.id = :id", Team.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
