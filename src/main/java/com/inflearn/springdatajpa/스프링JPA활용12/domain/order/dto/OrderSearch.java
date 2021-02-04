package com.inflearn.springdatajpa.스프링JPA활용12.domain.order.dto;

import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.vo.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

    public OrderSearch(String memberName, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }
}
