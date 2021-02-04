package com.inflearn.springdatajpa.연습용.domain.membertest1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberTestRepositoryTest {

    @Autowired
    private MemberTestRepository memberTestRepository;

    @Transactional
    @Test
    void findById() {
        // when
        MemberTest memberTest = memberTestRepository.findById(1L);

        // then
        System.out.println(memberTest.getTeam().getClass());
        System.out.println(memberTest.getTeam().getId());
        System.out.println(memberTest.getTeam());
    }
}