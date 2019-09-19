package com.mainul35.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class JdbcConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {

    }
}
