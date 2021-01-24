package com.inflearn.springdatajpa.domain.order.query;

import com.inflearn.springdatajpa.domain.order.dto.OrderItemQueryDto;
import com.inflearn.springdatajpa.domain.order.dto.OrderQueryDto;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class OrderQueryRepository {

    private final EntityManager em;

    public OrderQueryRepository(EntityManager em) {
        this.em = em;
    }

    public List<OrderQueryDto> findOrderQueryDto() {
        List<OrderQueryDto> orders = findOrders();
        orders.forEach(order -> {
            List<OrderItemQueryDto> orderItems = findOrderItems(order.getOrderId());
            order.setOrderItems(orderItems);
        });
        return orders;
    }

    public List<OrderQueryDto> finAllByDto_optimization() {
        List<OrderQueryDto> orders = findOrders();

        Set<Long> orderIds = orders.stream()
                .map(OrderQueryDto::getOrderId)
                .collect(Collectors.toSet());

        List<OrderItemQueryDto> orderItems = em.createQuery(
                "select new com.inflearn.springdatajpa.domain.order.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                " from OrderItem  oi" +
                " join oi.item i" +
                " where oi.order.id in :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));

        return orders.stream()
                .peek(order -> order.setOrderItems(orderItemMap.get(order.getOrderId())))
                .collect(Collectors.toList());
    }

    /**
     * OrderQueryDto를 조회하는데, 일대다 관계를 갖는 OrderItemQueryDto는 루트 쿼리 시점(findOrderQueryDto())에
     * 값을 채워넣지 못하므로 따로 분리해서 채워넣는 방식으로 진행해야한다.
     * @param orderId
     * @return
     */
    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery("select new com.inflearn.springdatajpa.domain.order.dto.OrderItemQueryDto(oi.order.id, i.name, oi.orderPrice, oi.count)" +
                " from OrderItem  oi" +
                " join oi.item i" +
                " where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    public List<OrderQueryDto> findOrders() {
        return em.createQuery("select new com.inflearn.springdatajpa.domain.order.dto.OrderQueryDto(o.id, m.username, o.orderDate, o.status, d.address)" +
                " from Order o" +
                " join o.member m" +
                " join o.delivery d", OrderQueryDto.class)
                .getResultList();
    }
}
