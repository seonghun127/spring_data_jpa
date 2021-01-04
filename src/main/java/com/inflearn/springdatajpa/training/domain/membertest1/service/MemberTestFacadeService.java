package com.inflearn.springdatajpa.training.domain.membertest1.service;

import com.inflearn.springdatajpa.training.domain.locker.Locker;
import com.inflearn.springdatajpa.training.domain.locker.LockerRepository;
import com.inflearn.springdatajpa.training.domain.membertest1.MemberTest;
import com.inflearn.springdatajpa.training.domain.membertest1.MemberTestRepository;
import com.inflearn.springdatajpa.training.domain.team.Team;
import com.inflearn.springdatajpa.training.domain.team.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MemberTestFacadeService {

    private final MemberTestRepository memberTestRepository;
    private final LockerRepository lockerRepository;
    private final TeamRepository teamRepository;

    public MemberTestFacadeService(MemberTestRepository memberTestRepository, LockerRepository lockerRepository, TeamRepository teamRepository) {
        this.memberTestRepository = memberTestRepository;
        this.lockerRepository = lockerRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Long save() {
        Team team = Team.builder()
                .name("teamA")
                .build();

        final MemberTest member = MemberTest.builder()
                .username("memberA")
                .build();

//        final Locker locker = Locker.builder()
//                .member(member)
//                .build();

        Long savedMemberId = memberTestRepository.save(member);
//        Long savedLockerId = lockerRepository.save(locker);
        Long savedTeamId = teamRepository.save(team);

        member.joinTeam(team);

        return savedTeamId;
    }

    public MemberTest findMember(Long id) {
//        return memberRepository.find(id);
        return memberTestRepository.findById(id);
    }

    public Locker findLocker(Long id) {
        return lockerRepository.find(id);
//        return lockerRepository.findWithJpql(id);
    }

    public Team findTeam(Long id) {
//        Team team = teamRepository.find(id);
//        log.info("the size of team.members is {}", team.getMembers().size());
//        return team;
        return teamRepository.findById(id);
    }
}
