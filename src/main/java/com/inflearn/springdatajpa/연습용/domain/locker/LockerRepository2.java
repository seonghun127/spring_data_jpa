package com.inflearn.springdatajpa.연습용.domain.locker;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository2 extends JpaRepository<Locker, Long> {

    List<Locker> findByIdIn(List<Long> ids);
}
