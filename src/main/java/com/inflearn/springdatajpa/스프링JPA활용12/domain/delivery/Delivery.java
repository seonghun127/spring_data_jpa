package com.inflearn.springdatajpa.스프링JPA활용12.domain.delivery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.common.vo.Address;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.delivery.vo.DeliveryStatus;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.Order;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
