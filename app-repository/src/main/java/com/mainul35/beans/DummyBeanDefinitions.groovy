package com.mainul35.beans;

import com.mainul35.model.GrantedAuthority;
import com.mainul35.model.UserDetails
import com.mainul35.repository.AuthorityRepository;
import com.mainul35.repository.CriteriaQueryRepository
import com.mainul35.repository.UserDetailsRepository;
import com.mainul35.security.enums.Role
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root
import javax.transaction.Transactional;
import java.util.List;

@Component
public class DummyBeanDefinitions {

    @Autowired
    private UserDetailsRepository userDetailsRepository

    @Autowired
    private AuthorityRepository authorityRepository;

    public void print() {

        EntityManager entityManager = userDetailsRepository.entityManager(UserDetails.class);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<UserDetails> cq = builder.createQuery( UserDetails.class );
        Root<UserDetails> root = cq.from( UserDetails.class );
        cq.select( root );
        cq.where(builder.equal( root.get( "username" ), "mainul35" ));

        List<UserDetails> users = entityManager.createQuery(cq).getResultList();
        System.out.println(users.toString());
    }

    void createUser() {
        GrantedAuthority authority = new GrantedAuthority()
        authority.setId(Role.CUSTOMER.getCode().toLong())
        authority.setAuthority(Role.CUSTOMER.getValue())
        authorityRepository.create(authority)

        UserDetails userDetails = new UserDetails();
        userDetails.setName("Mainul Hasan")
        userDetails.setUsername("mainul35")
        userDetails.setPassword("secret")
        userDetails.setAuthority(authority)

        userDetailsRepository.create(userDetails)

    }
}
