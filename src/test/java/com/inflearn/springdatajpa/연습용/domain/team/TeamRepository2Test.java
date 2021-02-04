package com.inflearn.springdatajpa.연습용.domain.team;

import org.hibernate.jpa.internal.util.PersistenceUtilHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TeamRepository2Test {

    @Autowired
    private TeamRepository2 teamRepository;

    @Transactional
    @Test
    void findById() {
        // when
        Team team = teamRepository.findById(1L).orElseThrow();

        // then
        System.out.println(team.getClass());
        System.out.println(team.getMemberTests().getClass());
        System.out.println(PersistenceUtilHelper.isLoaded(team.getMemberTests()));
        team.getMemberTests().forEach(memberTest -> System.out.println(memberTest.toString()));
        System.out.println(PersistenceUtilHelper.isLoaded(team.getMemberTests()));
    }
}