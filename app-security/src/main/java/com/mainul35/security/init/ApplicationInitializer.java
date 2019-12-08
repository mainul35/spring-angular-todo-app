package com.mainul35.security.init;

import com.mainul35.security.model.Authority;
import com.mainul35.security.model.User;
import com.mainul35.security.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ApplicationInitializer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInitializer.class);

    @PostConstruct
    public void init() {
        LOGGER.info(ANSI_GREEN + "Initializing Security Context" + ANSI_RESET);
        SecurityContextHolder.user = new User() {
            @Override
            public String getUsername() {
                return "anonymous";
            }

            @Override
            public String getPassword() {
                return UUID.randomUUID().toString();
            }

            public Collection<Authority> getAuthorities() {
                List<Authority> authorities = new ArrayList<>();
                Authority authority = new Authority() {
                    @Override
                    public String getAuthority() {
                        return "anonymous";
                    }
                };
                authorities.add(authority);
                return authorities;
            }
        };
    }
}
