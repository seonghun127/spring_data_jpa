package com.inflearn.springdatajpa.domain.order.dto;

import com.inflearn.springdatajpa.domain.order.vo.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
