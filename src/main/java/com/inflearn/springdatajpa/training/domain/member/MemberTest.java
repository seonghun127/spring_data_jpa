package com.inflearn.springdatajpa.training.domain.member;

import com.inflearn.springdatajpa.training.domain.locker.Locker;
import com.inflearn.springdatajpa.training.domain.team.Team;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MEMBER_TEST")
@Entity
@Getter
@NoArgsConstructor
public class MemberTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @OneToOne(mappedBy = "memberTest", fetch = FetchType.LAZY)
//    @OneToOne(mappedBy = "memberTest", fetch = FetchType.EAGER)
    private Locker locker;

    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Builder
    public MemberTest(String username, Locker locker) {
        this.username = username;
        this.locker = locker;
    }

    public void joinTeam(Team team) {
        this.team = team;
        team.getMemberTests().add(this);
    }
}
