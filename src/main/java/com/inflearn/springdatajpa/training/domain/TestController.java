package com.inflearn.springdatajpa.training.domain;

import com.inflearn.springdatajpa.training.domain.locker.Locker;
import com.inflearn.springdatajpa.training.domain.membertest1.service.MemberTestFacadeService;
import com.inflearn.springdatajpa.training.domain.team.Team;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MemberTestFacadeService memberService;

    public TestController(MemberTestFacadeService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/test")
    public Long save() {
        return memberService.save();
    }

    @GetMapping("/test/{id}")
    public String find(@PathVariable Long id) {
        return memberService.findMember(id).getUsername();
    }

    @GetMapping("/test/locker/{id}")
    public Long findLocker(@PathVariable Long id) {
        Locker locker = memberService.findLocker(id);
        return locker.getId();
    }

    @GetMapping("/test/team/{id}")
    public Long findTeam(@PathVariable Long id) {
        Team team = memberService.findTeam(id);
        return team.getId();
    }
}
