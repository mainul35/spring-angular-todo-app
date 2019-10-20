package com.mainul35.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public
class Department {
    @Id
    Long id;
    @Column
    String name;
}
