package org.service.proxy.config.v1_proxy.concreete_proxy;

import org.service.proxy.app.v2.OrderRepositoryV2;
import org.service.proxy.app.v2.OrderServiceV2;
import org.service.proxy.trace.TraceStatus;
import org.service.proxy.trace.logtrace.LogTrace;


public class OrderServiceConcreteProxy extends OrderServiceV2 {
    private final OrderServiceV2 target;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 target, LogTrace logTrace) {
        super(null);
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;
        try{
            status = logTrace.begin("OriginService.orderItem()");
            //target 실행
            target.orderItem(itemId);
            logTrace.end(status);
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
