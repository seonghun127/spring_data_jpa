package com.inflearn.springdatajpa.api;

import com.inflearn.springdatajpa.domain.common.vo.Address;
import com.inflearn.springdatajpa.domain.order.Order;
import com.inflearn.springdatajpa.domain.order.OrderRepository;
import com.inflearn.springdatajpa.domain.order.dto.OrderSearch;
import com.inflearn.springdatajpa.domain.order.vo.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * xToOne (ManyToOne, OneToOne)
 *  Order
 *  Order -> Member
 *  Order -> Delivery
 */
@RestController
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    public OrderSimpleApiController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * API에서 엔티티를 반환화는 상황
     *
     * 엔티티 자체를 API에서 반환할 때 양방향의 연관관계가 있다면 순환 참조가 발생하여
     * Serialize를 실패하고 stack overflow가 발생하여 에러가 난다.
     * -> 임시적으로 이 문제를 해결하려면 엔티티 레벨에서 jackson 라이브러리가
     * 인지하지 못하도록 양방향 연관관계에 @JsonIgnore 어노테이션을 추가해줘야한다. (물론 일시적 해결일뿐....)
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        final List<Order> orders = orderRepository.findAll(new OrderSearch("userA", OrderStatus.ORDER));

        // 순환참조 무한 루프를 해결하기 위해 강제 연관관계 엔티티 로딩
        orders.forEach(order -> {
            Hibernate.initialize(order.getMember());
            Hibernate.initialize(order.getDelivery());
        });

        return orders;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findAll(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getUsername(); // LAZY
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // LAZY
        }
    }
}
