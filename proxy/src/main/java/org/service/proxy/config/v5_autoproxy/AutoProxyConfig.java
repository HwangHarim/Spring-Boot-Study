package org.service.proxy.config.v5_autoproxy;

import org.service.proxy.config.AppV1Config;
import org.service.proxy.config.AppV2Config;
import org.service.proxy.config.v3_proxyfactory.LogTraceAdvice;
import org.service.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

    //@Bean
    public Advisor advisor1(LogTrace logTrace) {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*","order*","save*");
        //advice
        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }

    //@Bean
    public Advisor advisor2(LogTrace logTrace){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* org.service.proxy.app..*(..))");
        //advice
        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }


    @Bean
    public Advisor advisor3(LogTrace logTrace){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* org.service.proxy.app..*(..)) && !execution(* org.service.proxy.app..noLog(..))");
        //advice
        LogTraceAdvice logTraceAdvice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, logTraceAdvice);
    }
}
