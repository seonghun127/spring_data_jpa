package com.inflearn.springdatajpa.api;

import com.inflearn.springdatajpa.domain.common.vo.Address;
import com.inflearn.springdatajpa.domain.order.Order;
import com.inflearn.springdatajpa.domain.order.OrderRepository;
import com.inflearn.springdatajpa.domain.order.dto.OrderSearch;
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

    @GetMapping({"/api/v2/orders"})
    public List<OrderDto> ordersV2() {
        return orderRepository.findAll(new OrderSearch()).stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping({"/api/v3/orders"})
    public List<OrderDto> ordersV3() {
        return orderRepository.findAllWithItem().stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping({"/api/v3.1/orders"})
    public List<OrderDto> ordersV3_page(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "100") int limit
    ) {
        return orderRepository.findAllWithMemberDelivery(offset, limit).stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
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
