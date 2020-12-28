package com.inflearn.springdatajpa.training.domain.team;

import java.util.List;
import org.hibernate.jpa.internal.util.PersistenceUtilHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    @Test
    void findById() {
        // when
        Team team = teamRepository.findById(1L);

        // then
        System.out.println(team.getClass());
        System.out.println(team.getMemberTests().getClass());
        System.out.println(PersistenceUtilHelper.isLoaded(team.getMemberTests()));
        team.getMemberTests().forEach(memberTest -> System.out.println(memberTest.toString()));
        System.out.println(PersistenceUtilHelper.isLoaded(team.getMemberTests()));
    }

    @Transactional
    @Test
    void findAll() {
        // when
        List<Team> teams = teamRepository.findAll();

        // then
        System.out.println(teams.getClass());
        teams.forEach(team -> {
            System.out.println(team.getMemberTests().getClass());
            System.out.println(PersistenceUtilHelper.isLoaded(team.getMemberTests()));
            team.getMemberTests().forEach(memberTest -> System.out.println(memberTest.toString()));
            System.out.println(PersistenceUtilHelper.isLoaded(team.getMemberTests()));
        });
    }
}