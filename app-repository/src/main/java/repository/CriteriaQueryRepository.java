package repository;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

@PropertySource("classpath:application.properties")
abstract class CriteriaQueryRepository <T>{

    private SessionFactory sessionFactory = null;

    protected SessionFactory getSessionFactory(){
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.setProperties(getBuiltProperties("application-dev.properties"));

                List<Class<?>> classes = EntityScanner
                        .scanPackages("com.mainul35.entity").result();

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
        InputStream input = CriteriaQueryRepository.class.getClassLoader().getResourceAsStream(propertyFileName);
        properties.load(input);

        return properties;
    }
}