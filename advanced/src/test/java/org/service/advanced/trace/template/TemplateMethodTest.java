package org.service.advanced.trace.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.service.advanced.trace.template.code.AbstractTemplate;
import org.service.advanced.trace.template.code.SubClassLogic1;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직 1 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();
        log.info("비즈니스 로직 2 실행");
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    @Test
    void templateMethodV1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic1();
        template2.execute();
    }

    @Test
    void templateMethodV2(){
        AbstractTemplate abstractTemplate = new AbstractTemplate(){

            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        abstractTemplate.execute();

        AbstractTemplate abstractTemplate2 = new AbstractTemplate(){

            @Override
            protected void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        abstractTemplate2.execute();
    }
}
