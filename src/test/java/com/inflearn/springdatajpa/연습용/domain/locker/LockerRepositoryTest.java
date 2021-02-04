package com.inflearn.springdatajpa.연습용.domain.locker;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class LockerRepositoryTest {

    @Autowired
    private LockerRepository lockerRepository;

    @Test
    void findById() {
        // when
        Locker locker = lockerRepository.findById(1L);

        // then
        System.out.println(locker.getClass());
        System.out.println(locker.getMemberTest().getClass());
    }

    @Test
    @Transactional
    void findAll() {
        // 쿼리 확인 용
        List<Locker> results = lockerRepository.findAll();

        results.forEach(e -> {
            System.out.println(e.getMemberTest());
            System.out.println(e.getMemberTest().getLocker());
        });
    }
}