package com.inflearn.springdatajpa.스프링JPA활용12.domain.item;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final EntityManager em;

    public ItemRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
