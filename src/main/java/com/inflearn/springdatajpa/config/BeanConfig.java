package com.inflearn.springdatajpa.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.inflearn.springdatajpa.스프링JPA활용12",
        "com.inflearn.springdatajpa.스프링데이터JPA실전"
})
@EntityScan(basePackages = {
        "com.inflearn.springdatajpa.스프링JPA활용12",
        "com.inflearn.springdatajpa.스프링데이터JPA실전"
})
public class BeanConfig {

    /**
     * 엔티티 연관관계가 지연로딩일 때
     * 직렬화 시 프록시 객체를 알 수 없는 타입으로 인시하여 에러가 발생하기 때문에
     * 이를 빈으로 등록하여 프록시 객체를 null로 직렬화할 수 있도록 설정해준다.
     * 또는 주석을 풀어 프록시 객체를 강제 지연로딩시키게 할 수 있다.
     * @return
     */
    @Bean
    public Hibernate5Module hibernate5Module() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
//        hibernate5Module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        return hibernate5Module;
    }
}
