package com.mainnul35.security.entity

import javax.persistence.Entity

@Entity
class OAuthUser {
    Long id
    String username
    String password
}
