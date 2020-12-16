package com.inflearn.springdatajpa.training.domain.team;

import com.inflearn.springdatajpa.training.domain.member.MemberTest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<MemberTest> memberTests = new ArrayList<>();

    @Builder
    public Team(String name) {
        this.name = name;
    }
}
