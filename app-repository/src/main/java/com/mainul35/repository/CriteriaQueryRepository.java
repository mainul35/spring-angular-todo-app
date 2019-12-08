package com.mainul35.repository;

import com.mainul35.utils.AppUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public abstract class CriteriaQueryRepository <T extends Serializable> {
    private SessionFactory sessionFactory = null;

//    @Value("${spring.profiles.active}")
    private String activeProfile;

/*    private String DS_PROP_FILE_NAME;
    private String DS_URL = "datasource.url";
    private String DS_USER = "datasource.username";
    private String DS_PASSWORD = "datasource.password";
    private String DS_DRIVER_CLASS = "datasource.driverClassName";
    private String DS_SCHEMA = "datasource.schema";

    private String HIBERNATE_FILE_NAME= "hibernate/hibernate-dev.properties";
    private String HBM2DDL = "hibernate.hbm2ddl.auto";
    private String HBM_DIALECT = "hibernate.dialect";*/

    private Session session;
    private CriteriaBuilder criteriaBuilder;
    private Class<T> model;
    private Properties applicationProperties;

    CriteriaQueryRepository() {
        applicationProperties = AppUtil.getBuiltProperties("application.properties");
        activeProfile = applicationProperties.getProperty("spring.profiles.active");
    }
    /*public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        Properties properties = getBuiltProperties("db/db-"+activeProfile+".properties");
        dataSource.setDriverClass(properties.get(DS_DRIVER_CLASS).toString());
        dataSource.setJdbcUrl(properties.get(DS_URL).toString());
        dataSource.setUser(properties.get(DS_USER).toString());
        dataSource.setPassword(properties.get(DS_PASSWORD).toString());
        return dataSource;
    }*/

    private Session getSession() {
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        return Optional
                .of(this.session)
                .isPresent()
                ? this.session
                : createAndGetLocalSessionFactoryBean().openSession();
    }

    private CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        session.beginTransaction();
        return session.getCriteriaBuilder();
    }

    private CriteriaQuery<T> getCriteriaQuery (Class<T> model) {
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

    public EntityManager entityManager(Class<T> model){
        this.model = model;
        return createAndGetLocalSessionFactoryBean().createEntityManager();
    }

    protected SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = AppUtil.getBuiltProperties("hibernate/hibernate-"+ activeProfile +".properties");

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

    public List findAll() {

        return getSession().
                createQuery("from " + model.getName())
                .list();
    }

    public T create(T entity) {
        session = getSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        return entity;
    }

    public T update(T entity) {
        session = getSession();
        Transaction transaction = session.beginTransaction();
        entity =  (T) session.merge(entity);
        transaction.commit();
        return entity;
    }

    public void delete(T entity) {
        session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);

        delete(entity);
    }

    public T findOne(long id) {
        session = getSession();
        Transaction transaction = session.beginTransaction();
         T entity = (T) session.
                get(model, id);
         transaction.commit();
         return entity;
    }
}