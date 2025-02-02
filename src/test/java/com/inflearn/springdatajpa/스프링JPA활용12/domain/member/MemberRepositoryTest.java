package com.inflearn.springdatajpa.스프링JPA활용12.domain.member;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void testMember() {
        // given
        final Member member = Member.builder()
                .username("memberA")
                .build();
        final Long savedId = memberRepository.save(member);

        // when
        final Member result = memberRepository.findOne(savedId);

        // then
        assertThat(result.getId()).isEqualTo(member.getId());
        assertThat(result.getUsername()).isEqualTo(member.getUsername());
    }
}