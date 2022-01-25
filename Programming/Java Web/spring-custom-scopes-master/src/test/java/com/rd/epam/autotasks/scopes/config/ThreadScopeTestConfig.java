package com.rd.epam.autotasks.scopes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(ThreadScopeConfig.class)
public class ThreadScopeTestConfig {

    @Bean
    @Scope("thread")
    public Object threadBean1() {
        return new Object();
    }

    @Bean
    @Scope("thread")
    public Object threadBean2() {
        return new Object();
    }

}
