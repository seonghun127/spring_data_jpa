package com.inflearn.springdatajpa.api;

import com.inflearn.springdatajpa.domain.common.vo.Address;
import com.inflearn.springdatajpa.domain.order.Order;
import com.inflearn.springdatajpa.domain.order.OrderRepository;
import com.inflearn.springdatajpa.domain.order.dto.OrderSearch;
import com.inflearn.springdatajpa.domain.orderitem.OrderItem;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;

    public OrderApiController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 엔티티를 반환하고 있기 때문에 사용하면 안되는 API
     * 반환되는 Order는 다양한 엔티티와 양방향 연관관계를 가지고 있어
     * 직렬화 시점에 순환참조로 stack overflow가 발생한다.
     *
     * @return
     */
    @GetMapping({"/api/v1/orders"})
    public List<Order> ordersV1() {
        List<Order> orders = orderRepository.findAll(new OrderSearch());

        // 강제 연관관계 엔티티 즉시 로딩
        for (Order order : orders) {
            final String username = order.getMember().getUsername();
            final Address address = order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(orderItem -> orderItem.getItem().getName());
        }

        return orders;
    }
}
