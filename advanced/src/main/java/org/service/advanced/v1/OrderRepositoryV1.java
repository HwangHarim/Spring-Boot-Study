package org.service.advanced.v1;

import lombok.RequiredArgsConstructor;
import org.service.advanced.hellotrace.HelloTraceV1;
import org.service.advanced.trace.TraceStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {
    private final HelloTraceV1 trace;

    public void save(String itemId){
        TraceStatus status= null;
        try{
            status = trace.begin("OrderRepository,request()");
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }

    private void sleep(int millis){
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
