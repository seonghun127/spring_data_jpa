package com.inflearn.springdatajpa.training.domain.membertest1;

import com.inflearn.springdatajpa.training.domain.locker.Locker;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberTestRepository2Test {

    @Autowired
    private MemberTestRepository2 memberTestRepository;

    @Test
    void test() {
        // given
        MemberTest memberTest = new MemberTest(new Locker());
//        MemberTest memberTest = new MemberTest();
        MemberTest save = memberTestRepository.save(memberTest);
        MemberTest memberTest2 = new MemberTest(new Locker());
//        MemberTest memberTest2 = new MemberTest();
        MemberTest save2 = memberTestRepository.save(memberTest2);

        // when
        List<MemberTest> members = memberTestRepository.findByIdIn(Arrays.asList(save.getId(), save2.getId()));

        // then
//        for (MemberTest member : members) {
//            System.out.println(member.getLocker());
//        }
    }

    @Test
    @Transactional
    void findById() {
        MemberTest memberTest = memberTestRepository.findById(1L)
                .orElseThrow();

        System.out.println(memberTest.getTeam().getClass());
        System.out.println(memberTest.getTeam().getName());
    }
}