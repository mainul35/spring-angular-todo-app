package com.mainul35.domain

import groovy.transform.ToString

import javax.persistence.Entity

@Entity
@ToString
class Department {
    Long id
    String name
}
