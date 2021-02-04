package com.inflearn.springdatajpa.연습용.domain.membertest2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberTest2RepositoryTest {

    @Autowired
    private MemberTest2Repository memberTest2Repository;

    @Test
    void findById() {
        // when
        MemberTest2 member = memberTest2Repository.findById(1L)
                .orElseThrow();

        // then
        System.out.println(member.getClass());
//        System.out.println(member.getLocker().getClass());
    }
}