package com.mainul35.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public abstract class CriteriaQueryRepository <T>{
    private SessionFactory sessionFactory = null;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    private String DS_PROP_FILE_NAME;
    private String DS_URL = "datasource.url";
    private String DS_USER = "datasource.username";
    private String DS_PASSWORD = "datasource.password";
    private String DS_DRIVER_CLASS = "datasource.driverClassName";
    private String DS_SCHEMA = "datasource.schema";

    private String HIBERNATE_FILE_NAME= "hibernate/hibernate-dev.properties";
    private String HBM2DDL = "hibernate.hbm2ddl.auto";
    private String HBM_DIALECT = "hibernate.dialect";

    private Session session;
    private CriteriaBuilder criteriaBuilder;
    private Class<T> model;

    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Properties properties = getBuiltProperties("db/db-"+activeProfile+".properties");
        dataSource.setDriverClass(properties.get(DS_DRIVER_CLASS).toString());
        dataSource.setJdbcUrl(properties.get(DS_URL).toString());
        dataSource.setUser(properties.get(DS_USER).toString());
        dataSource.setPassword(properties.get(DS_PASSWORD).toString());
        return dataSource;
    }

    protected Session getSession() {
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        return Optional
                .of(this.session)
                .isPresent()
                ? this.session
                : createAndGetLocalSessionFactoryBean().openSession();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        session.beginTransaction();
        return session.getCriteriaBuilder();
    }

    protected CriteriaQuery<T> getCriteriaQuery (Class<T> model) {
        this.model = model;
        try {
            this.criteriaBuilder = getCriteriaBuilder();
        } catch (Exception e) {
            System.err.println("Failed to establish connection with database");
            e.printStackTrace();
        }
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(model);
        return criteriaQuery;
    }

    protected EntityManager entityManager(){
        return createAndGetLocalSessionFactoryBean().createEntityManager();
    }

    protected EntityManager entityManager(Class<T> model){
        this.model = model;
        return createAndGetLocalSessionFactoryBean().createEntityManager();
    }

    protected SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = getBuiltProperties("hibernate/hibernate-"+activeProfile+".properties");

                configuration.setProperties(settings);
                if (model != null) {
                    configuration.addAnnotatedClass(model);
                }
                StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    protected Properties getBuiltProperties(String propertyFileName) {
        Properties properties = new Properties();
        InputStream input = CriteriaQueryRepository.class
                .getClassLoader().getResourceAsStream(propertyFileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}