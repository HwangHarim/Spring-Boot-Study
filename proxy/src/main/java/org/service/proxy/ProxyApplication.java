package org.service.proxy;


import org.service.proxy.config.v1_proxy.ConcreteProxyConfig;
import org.service.proxy.config.v1_proxy.InterfaceProxyConfig;
import org.service.proxy.config.v2_dynamicproxy.DynamicProxyBasicConfig;
import org.service.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import org.service.proxy.config.v3_proxyfactory.ProxyFactoryConfigV1;
import org.service.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import org.service.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import org.service.proxy.config.v5_autoproxy.AutoProxyConfig;
import org.service.proxy.config.v6_aop.AopConfig;
import org.service.proxy.trace.logtrace.LogTrace;
import org.service.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import(InterfaceProxyConfig.class)
//@Import(ConcreteProxyConfig.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(DynamicProxyFilterConfig.class)
//@Import(ProxyFactoryConfigV1.class)
//@Import(ProxyFactoryConfigV2.class)
//@Import(BeanPostProcessorConfig.class)
//@Import(AutoProxyConfig.class)
@Import(AopConfig.class)
@SpringBootApplication(scanBasePackages = "org.service.proxy.app.v3")
public class ProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class, args);
    }


    @Bean
    public LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
