package org.service.proxy.config.v1_proxy;

import org.service.proxy.app.v2.OrderControllerV2;
import org.service.proxy.app.v2.OrderRepositoryV2;
import org.service.proxy.app.v2.OrderServiceV2;
import org.service.proxy.config.v1_proxy.concreete_proxy.OrderControllerConcreteProxy;
import org.service.proxy.config.v1_proxy.concreete_proxy.OrderRepositoryConcreteProxy;
import org.service.proxy.config.v1_proxy.concreete_proxy.OrderServiceConcreteProxy;
import org.service.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace){
        OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(orderController, logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace){
        OrderServiceV2 orderService = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(orderService, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
        OrderRepositoryV2 orderRepository = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(orderRepository, logTrace);
    }
}
