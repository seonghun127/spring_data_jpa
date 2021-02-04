package com.inflearn.springdatajpa.연습용.domain.locker;

import com.inflearn.springdatajpa.연습용.domain.membertest2.MemberTest2;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "LOCKER")
@Entity
@Getter
@NoArgsConstructor
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_TEST2_ID", nullable = false)
    private MemberTest2 memberTest;

    @Builder
    public Locker(MemberTest2 member) {
        this.memberTest = member;
    }
}
