package com.mainul35.security.util;

import com.mainul35.security.enums.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.METHOD, ElementType.TYPE})
public @interface Secured {

    Role[] value();

}
