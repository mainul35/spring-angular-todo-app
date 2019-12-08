package com.mainul35.security.common.aop;

import com.mainul35.security.enums.Role;
import com.mainul35.security.util.Secured;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
public class SecurityInterceptor {

    @Autowired
    private static HttpSession httpSession;

    @Around("@annotation(com.mainul35.security.util.Secured)")
    public Object intercept(final ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        System.out.println(Arrays.toString(method.getParameters()));

        System.out.println(httpSession.getAttribute("user"));

        Secured secured = method.getAnnotation(Secured.class);
        Role[] roles = (Role[])secured.value();
//        Arrays.asList(roles).forEach(r-> {
//            System.out.println(r.toString());
//        });
        /*for (Role role: roles) {
            System.out.println("Checking security for " + role.toString());
        }*/

        return point.proceed();
    }
}
