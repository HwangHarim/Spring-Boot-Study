package org.service.advanced.treadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.service.advanced.treadlocal.code.ThreadService;

@Slf4j
public class ThreadServiceTest {
    private ThreadService threadService = new ThreadService();

    @Test
    void field(){
        log.info("main Start");
        Runnable userA =() ->{
            threadService.logic("userA");
        };
        Runnable userB =() ->{
            threadService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadA.setName("thread-B");

        threadA.start();
        sleep(2000);
        threadB.start();
        sleep(3000);
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
