package com.inflearn.springdatajpa.연습용.domain.membertest1;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTestRepository2 extends JpaRepository<MemberTest, Long> {

    List<MemberTest> findByIdIn(List<Long> ids);
}
