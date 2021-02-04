package com.inflearn.springdatajpa.스프링JPA활용12.domain.order.dto;

import com.inflearn.springdatajpa.스프링JPA활용12.domain.common.vo.Address;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.Order;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.vo.OrderStatus;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getUsername();
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress();
    }

    public SimpleOrderDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
