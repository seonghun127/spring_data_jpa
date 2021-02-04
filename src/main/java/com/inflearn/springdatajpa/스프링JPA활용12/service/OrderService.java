package com.inflearn.springdatajpa.스프링JPA활용12.service;

import com.inflearn.springdatajpa.스프링JPA활용12.domain.delivery.Delivery;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.item.Item;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.item.ItemRepository;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.member.Member;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.member.MemberRepository;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.Order;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.OrderRepository;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.dto.OrderSearch;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.orderitem.OrderItem;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, MemberRepository memberRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
