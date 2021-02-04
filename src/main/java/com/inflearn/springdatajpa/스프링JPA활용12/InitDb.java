package com.inflearn.springdatajpa.스프링JPA활용12;

import com.inflearn.springdatajpa.스프링JPA활용12.domain.common.vo.Address;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.delivery.Delivery;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.item.Book;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.member.Member;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.order.Order;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.orderitem.OrderItem;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitDb {

    private final InitService initService;

    public InitDb(InitService initService) {
        this.initService = initService;
    }

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    static class InitService {

        private final EntityManager entityManager;

        InitService(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        public void dbInit1() {
            final Member member = createMember("userA", "서울", "1");
            entityManager.persist(member);

            final Book book1 = createBook("JPA1 BOOK", 10000, 100);
            entityManager.persist(book1);

            final Book book2 = createBook("JPA2 BOOK", 20000, 100);
            entityManager.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            final Delivery delivery = createDelivery(member);

            final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            entityManager.persist(order);
        }

        public void dbInit2() {
            final Member member = createMember("userB", "진주", "2");
            entityManager.persist(member);

            final Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            entityManager.persist(book1);

            final Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            entityManager.persist(book2);

            final OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            final OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);

            final Delivery delivery = createDelivery(member);

            final Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            entityManager.persist(order);
        }

        private Member createMember(String username, String city, String street) {
            final Member member = new Member();
            member.setUsername(username);
            member.setAddress(new Address(city, street, "1111"));
            return member;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            final Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        private Delivery createDelivery(Member member) {
            final Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }
}

