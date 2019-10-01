package com.mainul35.init

import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

//@Component
@CompileStatic
class BootStrap {
//    @Transactional
    void init() {
//        def dep = Department.createCriteria()
//        def results = dep.list (max: 10, offset: 10) {
//            and {
//                between("id", 0, 1000)
//            }
//            order("id", "desc")
//        }
        System.out.println("App running");
    }
}
