package com.inflearn.springdatajpa.스프링데이터JPA실전.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
