package com.mainul35

import groovy.transform.CompileStatic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource

@CompileStatic
@SpringBootApplication
@PropertySource("classpath:application.properties")
public class TodoAppApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(TodoAppApplication.class, args);
    }

/*
    @Bean
    ImplBeanDefinitions implBeanDefinitions() {
        return new ImplBeanDefinitions();
    }
*/

    /*@Value("${spring.datasource.url}")
    private String datasourceUrl

    @Value("${spring.datasource.username}")
    private String datasourceUsername

    @Value("${spring.datasource.password}")
    private String datasourcePassword

    @Value("${postgres.driverClassName}")
    private String driverClassName*/

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App running")
    }
}
