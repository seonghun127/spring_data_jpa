package com.inflearn.springdatajpa.service;

import com.inflearn.springdatajpa.domain.member.Member;
import com.inflearn.springdatajpa.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setUsername("kim");

        // when
        Long savedId = memberService.join(member);

        // then
        assertThat(member).isEqualTo(memberRepository.find(savedId));
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setUsername("kim");

        Member member2 = new Member();
        member2.setUsername("kim");

        // when
        memberService.join(member1);

        // then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}