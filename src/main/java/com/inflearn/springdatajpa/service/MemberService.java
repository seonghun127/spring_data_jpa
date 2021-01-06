package com.inflearn.springdatajpa.service;

import com.inflearn.springdatajpa.domain.member.Member;
import com.inflearn.springdatajpa.domain.member.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        // 이 로직은 multi thread 환경에선 무조건적 유효성 보장을 할 수 없기 때문에
        // 최후 방어선 구축을 위해 DB의 username 칼럼에 유니크 제약 조건을 걸어주는 것이 좋다.
        // -> 동시성 이슈를 DB 단에서 해결
        List<Member> members = memberRepository.findByName(member.getUsername());

        if (!members.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String username) {
        Member member = memberRepository.findOne(id);
        member.setUsername(username);
    }
}
