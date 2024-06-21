package org.service.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.service.advanced.trace.strategy.code.template.Callback;
import org.service.advanced.trace.strategy.code.template.TimeLogTemplate;

@Slf4j
public class TemplateCallbackTest {

    @Test
    void callBackV1(){
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        template.execute(new Callback() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    void callBackV2(){
        TimeLogTemplate template = new TimeLogTemplate();

        template.execute(() -> log.info("비즈니스 로직1 실행"));
        template.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
