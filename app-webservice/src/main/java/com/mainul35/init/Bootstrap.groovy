package com.mainul35.init

import com.mainul35.beans.DummyBeanDefinitions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Bootstrap {

    @Autowired
    static DummyBeanDefinitions beanDefinitions;

}
