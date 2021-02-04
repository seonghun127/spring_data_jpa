package com.inflearn.springdatajpa.연습용.domain.membertest2;

import com.inflearn.springdatajpa.연습용.domain.locker.Locker;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "MEMBER_TEST2")
@Entity
@Getter
@NoArgsConstructor
public class MemberTest2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @OneToOne(mappedBy = "memberTest", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @OneToOne(mappedBy = "memberTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Locker locker;

    @Builder
    public MemberTest2(String username) {
        this.username = username;
    }
}
