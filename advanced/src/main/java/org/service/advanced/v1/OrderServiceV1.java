package org.service.advanced.v1;

import lombok.RequiredArgsConstructor;
import org.service.advanced.hellotrace.HelloTraceV1;
import org.service.advanced.trace.TraceStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 repositoryV0;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId){
        TraceStatus status= null;
        try{
            status = trace.begin("OrderService,request()");
            repositoryV0.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }

    }
}
