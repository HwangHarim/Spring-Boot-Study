package org.service.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.service.proxy.cglib.code.TimeMethodInterceptor;
import org.service.proxy.common.service.ConcreteService;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib(){
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class);
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();

        log.info("target class={}", target.getClass());
        log.info("proxy class={}", proxy.getClass());

        proxy.call();
    }
}
