package org.service.proxy.config.v6_aop;

import org.service.proxy.config.AppV1Config;
import org.service.proxy.config.AppV2Config;
import org.service.proxy.config.v6_aop.aspect.LogTraceAspect;
import org.service.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

    @Bean
    public LogTraceAspect aspect (LogTrace logTrace){
        return new LogTraceAspect(logTrace);
    }
}
