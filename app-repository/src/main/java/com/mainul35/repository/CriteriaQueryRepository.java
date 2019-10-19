package com.mainul35.repository;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@PropertySource("classpath:application.properties")
public abstract class CriteriaQueryRepository <T>{

    private SessionFactory sessionFactory = null;

    private Session session;
    private CriteriaBuilder criteriaBuilder;
    private Class<T> model;

    protected Session getSession() {
        this.session = getSessionFactory().getCurrentSession();
        return Optional
                .of(this.session)
                .isPresent()
                ? this.session
                : getSessionFactory().openSession();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return getSession().getCriteriaBuilder();
    }

    protected CriteriaQuery<T> getCriteriaQuery () {
        this.criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(model);
        return criteriaQuery;
    }

    protected SessionFactory getSessionFactory(){
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperties(getBuiltProperties("application.properties"));

                List<Class<?>> classes = EntityScanner
                        .scanPackages("com.mainul35.model").result();

                for (Class<?> annotatedClass : classes) {
                    configuration.addAnnotatedClass(annotatedClass);
                }
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    protected Properties getBuiltProperties(String propertyFileName) throws IOException {
        Properties properties = new Properties();
        InputStream input = CriteriaQueryRepository.class
                .getClassLoader().getResourceAsStream(propertyFileName);
        properties.load(input);

        return properties;
    }
}