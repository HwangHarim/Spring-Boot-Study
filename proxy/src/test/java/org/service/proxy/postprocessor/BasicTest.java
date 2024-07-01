package org.service.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class BasicTest {

    @Test
    void basicConfig(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

        //A는 빈으로 등록
       A a =  applicationContext.getBean("bean_A", A.class);
       a.helloA();

       Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> applicationContext.getBean(B.class));
    }

    @Slf4j
    @Configuration
    static class BasicConfig{
        @Bean(name = "bean_A")
        public A a(){
            return new A();
        }

    }

    @Slf4j
    static class A{
        public void helloA(){
            log.info("helloA");
        };
    }

    @Slf4j
    static class B{
        public void helloB(){
            log.info("helloB");
        };
    }
}
