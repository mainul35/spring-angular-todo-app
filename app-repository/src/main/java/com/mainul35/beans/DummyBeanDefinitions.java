package com.mainul35.beans;

import com.mainul35.model.User;
import com.mainul35.repository.CriteriaQueryRepository;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class DummyBeanDefinitions extends CriteriaQueryRepository <User>{
    /*static void main(String[] args) {
        List<Integer> list = [73, 67, 38, 33]
        println gradingStudents(list)
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        grades.forEach({ i ->
            if (i > 37 ) {
                if (i > 40 && (i + 2) % 5 == 0) {
                    i += 2;
                } else if (i <= 40) {
                    i = 40;
                }
            }
        });
        return grades;
    }*/

    public void print() {
        /*CriteriaBuilder builder = getCriteriaBuilder();*/
        /*CriteriaQuery<User> query = getCriteriaQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        query.select(userRoot);
        TypedQuery<User> q = entityManager().createQuery(query);

        List<User> users = q.getResultList();*/

        EntityManager entityManager = entityManager(User.class);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cq = builder.createQuery( User.class );
        Root<User> root = cq.from( User.class );
        cq.select( root );
        cq.where(builder.equal( root.get( "name" ), "John Doe" ));

        List<User> users = entityManager.createQuery(cq).getResultList();
        System.out.println(users.toString());
    }
}
