package com.inflearn.springdatajpa.domain;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void testMember() {
        // given
        final Member member = Member.builder()
                .username("memberA")
                .build();
        final Long savedId = memberRepository.save(member);

        // when
        final Member result = memberRepository.find(savedId);

        // then
        assertThat(result.getId()).isEqualTo(member.getId());
        assertThat(result.getUsername()).isEqualTo(member.getUsername());
    }
}