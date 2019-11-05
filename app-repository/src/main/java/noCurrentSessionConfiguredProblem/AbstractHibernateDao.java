package noCurrentSessionConfiguredProblem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable>
        implements IGenericDao<T> {
    private Class<T> persistentClass;

    SessionFactory sessionFactory;

    @Autowired
    public AbstractHibernateDao(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }

        sessionFactory = factory.unwrap(SessionFactory.class);

        try {
            persistentClass = ((Class<T>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (Exception e) {

        }
    }

    public T findOne(long id) {

        return (T) getCurrentSession().
                get(persistentClass, id);
    }

    public List findAll() {

        return getCurrentSession().
                createQuery("from " + persistentClass.getName())
                .list();
    }

    public T create(T entity) {
        getCurrentSession().saveOrUpdate(entity);

        return entity;
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(long entityId) {
        T entity = findOne(entityId);

        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
