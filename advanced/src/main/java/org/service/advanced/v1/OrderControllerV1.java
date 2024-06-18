package org.service.advanced.v1;

import lombok.RequiredArgsConstructor;
import org.service.advanced.hellotrace.HelloTraceV1;
import org.service.advanced.trace.TraceStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {

    private final OrderServiceV1 service;
    private final HelloTraceV1 trace;

    @GetMapping("/v1/request")
    public String request(String itemId){
        TraceStatus status= null;
        try{
            status = trace.begin("OrderController,request()");
            service.orderItem(itemId);
            trace.end(status);
            return "ok";
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
