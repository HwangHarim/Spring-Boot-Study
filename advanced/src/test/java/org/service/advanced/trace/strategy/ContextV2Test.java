package org.service.advanced.trace.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.service.advanced.trace.strategy.code.strategy.ContextV2;
import org.service.advanced.trace.strategy.code.strategy.Strategy;
import org.service.advanced.trace.strategy.code.strategy.StrategyLogic1;
import org.service.advanced.trace.strategy.code.strategy.StrategyLogic2;

@Slf4j
public class ContextV2Test {

    @Test
    void strategyV1() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    @Test
    void strategyV2() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }
    @Test
    void strategyV4() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(() -> log.info("비즈니스 로직1 실행"));
        contextV2.execute(() -> log.info("비즈니스 로직2 실행"));
    }
}
