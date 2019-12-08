package com.mainul35.repository

import com.mainul35.model.GrantedAuthority
import org.springframework.stereotype.Repository

import javax.transaction.Transactional

@Repository
@Transactional
class AuthorityRepository extends CriteriaQueryRepository<GrantedAuthority>{

    AuthorityRepository() {
        super.entityManager(GrantedAuthority.class)
    }
}
