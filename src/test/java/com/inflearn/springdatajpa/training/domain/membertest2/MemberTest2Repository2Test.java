package com.inflearn.springdatajpa.training.domain.membertest2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberTest2Repository2Test {

    @Autowired
    private MemberTest2Repository2 memberTest2Repository;

    @Test
    void findById() {
        // when
        MemberTest2 memberTest2 = memberTest2Repository.findById(1L);

        // then
        System.out.println(memberTest2.getClass());
    }

    @Test
    void findAll() {
        // 쿼리 로그 확인 용
        memberTest2Repository.findAll();
    }
}