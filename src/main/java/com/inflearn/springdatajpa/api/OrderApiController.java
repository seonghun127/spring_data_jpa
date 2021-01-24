package com.inflearn.springdatajpa.api;

import com.inflearn.springdatajpa.domain.common.vo.Address;
import com.inflearn.springdatajpa.domain.order.Order;
import com.inflearn.springdatajpa.domain.order.OrderRepository;
import com.inflearn.springdatajpa.domain.order.dto.OrderFlatDto;
import com.inflearn.springdatajpa.domain.order.dto.OrderQueryDto;
import com.inflearn.springdatajpa.domain.order.dto.OrderSearch;
import com.inflearn.springdatajpa.domain.order.query.OrderQueryRepository;
import com.inflearn.springdatajpa.domain.order.vo.OrderStatus;
import com.inflearn.springdatajpa.domain.orderitem.OrderItem;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    public OrderApiController(OrderRepository orderRepository, OrderQueryRepository orderQueryRepository) {
        this.orderRepository = orderRepository;
        this.orderQueryRepository = orderQueryRepository;
    }

    /**
     * 엔티티를 반환하고 있기 때문에 사용하면 안되는 API
     * 반환되는 Order는 다양한 엔티티와 양방향 연관관계를 가지고 있어
     * 직렬화 시점에 순환참조로 stack overflow가 발생한다.
     *
     * @return
     */
    @GetMapping("/api/v1/orders")
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

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        return orderRepository.findAll(new OrderSearch()).stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        return orderRepository.findAllWithItem().stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV3_page(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "100") int limit
    ) {
        return orderRepository.findAllWithMemberDelivery(offset, limit).stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4() {
        return orderQueryRepository.findOrderQueryDto();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5() {
        return orderQueryRepository.finAllByDto_optimization();
    }

    /**
     * 데이터를 플랫하게 필요한 모든 테이블을 조인해서 한번에 가져온다. (쿼리 발생은 1번)
     * 일대다 조인이 포함되어있기 때문에 데이터 뻥튀기가 발생 -> 중복을 제거하는 로직 필요
     * DB 통신은 오로지 1번만 발생하되, 메모리에 플랫한 데이터를 모두 올려서 응답 payload에 맞게 가공하는 작업이 필요하다.
     * 응답 payload가 복잡하다면 가공하는 로직도 복잡해질 수 있다.
     * 데이터 뻥튀기가 발생(일대다 조인 + DTO 반환)하기 때문에 페이징 처리가 불가하하다.
     * 중복을 제거하고 데이터를 가공하는 작업은 pass
     * 쿼리는 1번만 발생하는 장점이 있지만 V5 API가 더 좋다고 생각한다. 이와 같은 방식은 현업에서도 사용하지 않을 듯 싶다.
     * @return
     */
    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> ordersV6() {
        return orderQueryRepository.findAllByDto_flat();
    }

    @Data
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            this.orderId = order.getId();
            this.name = order.getMember().getUsername();
            this.orderDate = order.getOrderDate();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
            this.orderItems = order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
