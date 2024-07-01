package org.service.proxy.config.v4_postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.service.proxy.config.AppV1Config;
import org.service.proxy.config.AppV2Config;
import org.service.proxy.config.v3_proxyfactory.LogTraceAdvice;
import org.service.proxy.config.v4_postprocessor.postprocessor.PackageLogTracePostProcessor;
import org.service.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

    @Bean
    public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logTrace){
        return new PackageLogTracePostProcessor("org.service.proxy.app", getAdvisor(logTrace));
    }

    private Advisor getAdvisor(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*","order*","save*");
        //advice
        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
