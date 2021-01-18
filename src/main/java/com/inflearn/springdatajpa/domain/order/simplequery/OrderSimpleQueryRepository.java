package com.inflearn.springdatajpa.domain.order.simplequery;

import com.inflearn.springdatajpa.domain.order.dto.SimpleOrderDto;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public OrderSimpleQueryRepository(EntityManager em) {
        this.em = em;
    }

    public List<SimpleOrderDto> findOrderDtos() {
        return em.createQuery("select new com.inflearn.springdatajpa.domain.order.dto.SimpleOrderDto(o.id, m.username, o.orderDate, o.status, d.address)" +
                " from Order o" +
                " join o.member m" +
                " join o.delivery d", SimpleOrderDto.class
        ).getResultList();
    }
}
