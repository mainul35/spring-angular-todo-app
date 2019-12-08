package com.mainul35.repository

import com.mainul35.model.UserDetails
import org.springframework.stereotype.Repository

import javax.transaction.Transactional

@Repository
@Transactional
class UserDetailsRepository extends CriteriaQueryRepository<UserDetails> {
    UserDetailsRepository() {
        super.entityManager(UserDetails.class)
    }
}
