package org.service.advanced.trace.treadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.service.advanced.trace.treadlocal.code.FieldService;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field(){
        log.info("main Start");
        Runnable userA =() ->{
            fieldService.logic("userA");
        };
        Runnable userB =() ->{
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadA.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();
        sleep(1000);
        log.info("main exit");
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
