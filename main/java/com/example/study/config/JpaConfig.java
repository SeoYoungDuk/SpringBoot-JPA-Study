package com.example.study.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing//JPA 감시를 활성화 시키겠다.
public class JpaConfig {

}
