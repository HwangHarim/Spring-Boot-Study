package org.service.advanced.config;

import lombok.Builder;
import org.service.advanced.trace.logtrace.FieldLogTrace;
import org.service.advanced.trace.logtrace.LogTrace;
import org.service.advanced.trace.logtrace.ThreadLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        //return new FieldLogTrace();
        return new ThreadLogTrace();
    }
}
