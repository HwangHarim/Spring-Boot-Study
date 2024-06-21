package org.service.advanced.v5;

import lombok.RequiredArgsConstructor;
import org.service.advanced.callback.TraceCallback;
import org.service.advanced.callback.TraceTemplate;
import org.service.advanced.trace.logtrace.LogTrace;
import org.service.advanced.trace.template.AbstractTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {

        template.execuet("OrderService.orderItem()", (TraceCallback<Void>) () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
