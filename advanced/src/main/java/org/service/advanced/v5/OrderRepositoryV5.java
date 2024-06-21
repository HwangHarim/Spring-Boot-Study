package org.service.advanced.v5;

import lombok.RequiredArgsConstructor;
import org.service.advanced.callback.TraceCallback;
import org.service.advanced.callback.TraceTemplate;
import org.service.advanced.trace.logtrace.LogTrace;
import org.service.advanced.trace.template.AbstractTemplate;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.stereotype.Repository;


@Repository
public class OrderRepositoryV5 {

    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }


    public void save(String itemId) {
        template.execuet("OrderRepository.save()", (TraceCallback<Void>) () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
