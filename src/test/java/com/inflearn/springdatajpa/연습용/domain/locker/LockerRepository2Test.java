package com.inflearn.springdatajpa.연습용.domain.locker;

import org.hibernate.jpa.internal.util.PersistenceUtilHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class LockerRepository2Test {

    @Autowired
    private LockerRepository2 lockerRepository;

    @Test
    @Transactional
    void findById() {
        // when
        Locker result = lockerRepository.findById(1L)
                .orElseThrow();

        // then
        System.out.println(PersistenceUtilHelper.isLoaded(result.getMemberTest()));
        System.out.println(result.getMemberTest());
    }
}