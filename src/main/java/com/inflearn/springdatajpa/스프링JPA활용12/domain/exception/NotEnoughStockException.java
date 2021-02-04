package com.inflearn.springdatajpa.스프링JPA활용12.domain.exception;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException(String message) {
        super(message);
    }
}
