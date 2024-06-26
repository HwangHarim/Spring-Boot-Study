package org.service.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import org.service.proxy.app.v1.OrderRepositoryV1;
import org.service.proxy.app.v1.OrderServiceV1;
import org.service.proxy.trace.TraceStatus;
import org.service.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1{
    private final OrderServiceV1 target;
    private final LogTrace logTrace;

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
