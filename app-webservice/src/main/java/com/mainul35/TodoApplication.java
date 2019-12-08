package com.mainul35;

import com.mainul35.beans.DummyBeanDefinitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TodoApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    DummyBeanDefinitions beanDefinitions;

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App running");
//        beanDefinitions.createUser();
        beanDefinitions.print();
    }

    @Override
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(TodoApplication.class);
    }
}
