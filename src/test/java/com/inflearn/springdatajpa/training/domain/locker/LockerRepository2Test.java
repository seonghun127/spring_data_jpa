package com.inflearn.springdatajpa.training.domain.locker;

import com.inflearn.springdatajpa.domain.member.Member;
import com.inflearn.springdatajpa.training.domain.member.MemberTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LockerRepository2Test {

    @Autowired
    private LockerRepository2 lockerRepository;

    @Test
    void findById() {
        // given
        MemberTest memberTest = new MemberTest();
        Locker locker = new Locker(memberTest);
        Locker save = lockerRepository.save(locker);

        // when
        Locker result = lockerRepository.findById(save.getId())
                .orElseThrow();

        // then
        System.out.println(result.getMemberTest());
    }

    @Test
    void findByIdIn() {
        // given
        MemberTest member1 = new MemberTest();
        MemberTest member2 = new MemberTest();
        Locker locker = new Locker(member1);
        Locker save = lockerRepository.save(locker);
        Locker locker2 = new Locker(member2);
        Locker save2 = lockerRepository.save(locker2);

        // when
        List<Locker> lockers = lockerRepository.findByIdIn(Arrays.asList(save.getId(), save2.getId()));

        // then
        for (Locker locker1 : lockers) {
            System.out.println(locker1.getMemberTest());
        }
    }
}