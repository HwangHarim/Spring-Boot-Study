package org.service.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.service.advanced.trace.strategy.code.strategy.ContextV1;
import org.service.advanced.trace.strategy.code.strategy.Strategy;
import org.service.advanced.trace.strategy.code.strategy.StrategyLogic1;
import org.service.advanced.trace.strategy.code.strategy.StrategyLogic2;

@Slf4j
public class ContextV1Test {
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
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();
        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV2.execute();
    }

    @Test
    void strategyV2(){
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("비니스 로직 1 실행");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategy1);
        contextV1.execute();

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("비니스 로직 2 실행");
            }
        };
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.execute();
    }

    @Test
    void strategyV3(){
        ContextV1 contextV1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비니스 로직 1 실행");
            }
        });
        contextV1.execute();
        //option + command + N -> 인라인 합치기
        ContextV1 contextV2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비니스 로직 2 실행");
            }
        });
        contextV2.execute();
    }

    @Test
    void strategyV4(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("비니스 로직 1 실행"));
        contextV1.execute();
        //option + command + N -> 인라인 합치기
        ContextV1 contextV2 = new ContextV1(() -> log.info("비니스 로직 2 실행"));
        contextV2.execute();
    }
}
