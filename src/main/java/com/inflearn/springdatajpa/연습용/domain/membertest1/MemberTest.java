package com.inflearn.springdatajpa.연습용.domain.membertest1;

import com.inflearn.springdatajpa.연습용.domain.locker.Locker;
import com.inflearn.springdatajpa.연습용.domain.team.Team;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

//    @OneToOne(mappedBy = "memberTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @OneToOne(mappedBy = "memberTest", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Locker locker;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Builder
    public MemberTest(String username, Locker locker) {
        this.username = username;
//        this.locker = locker;
    }

    @Builder
    public MemberTest(Locker locker) {
//        this.locker = locker;
    }

    public MemberTest(Team team) {
        this.team = team;
    }

    public void joinTeam(Team team) {
        this.team = team;
//        team.getMemberTests().add(this);
    }
}
