package com.rd.epam.autotasks.scopes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(JustASecondScopeConfig.class)
public class JustASecondScopeTestConfig {

    @Bean
    @Scope("justASecond")
    public Object jasBean1() {
        return new Object();
    }

    @Bean
    @Scope("justASecond")
    public Object jasBean2() {
        return new Object();
    }

}
