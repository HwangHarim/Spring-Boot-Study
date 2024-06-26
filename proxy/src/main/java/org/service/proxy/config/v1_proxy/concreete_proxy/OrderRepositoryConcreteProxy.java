package org.service.proxy.config.v1_proxy.concreete_proxy;

import lombok.RequiredArgsConstructor;
import org.service.proxy.app.v2.OrderRepositoryV2;
import org.service.proxy.trace.TraceStatus;
import org.service.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {
    private final OrderRepositoryV2 target;
    private final LogTrace trace;
    //control + o 하면 오버라이드 메서드
    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try{
            status = trace.begin("OriginRepository.request()");
            //target 실행
            target.save(itemId);
            trace.end(status);
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
