package org.service.proxy.advisor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.service.proxy.common.advice.TimeAdvice;
import org.service.proxy.common.service.ServiceImpl;
import org.service.proxy.common.service.ServiceInterface;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {

    @Test
    void advisorTest1(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("직접 만든 pointCut")
    void advisorTest2(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    @Test
    @DisplayName("Spring이 만든 pointCut")
    void advisorTest3(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("save");
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
        proxy.save();
        proxy.find();
    }

    static class MyPointcut implements Pointcut{

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher{

        private String matchName = "save";

        //option+command+f -> 필드멤버 분리
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
           boolean result = method.getName().equals(matchName);
           log.info("Pointcut 호출 = {} targetClass = {}",method.getName(), method.getClass());
            log.info("result = {}", result);
           return result;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
