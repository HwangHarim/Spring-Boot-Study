package org.service.proxy.config.v1_proxy.interface_proxy;

import lombok.RequiredArgsConstructor;
import org.service.proxy.app.v1.OrderControllerV1;
import org.service.proxy.trace.TraceStatus;
import org.service.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace logTrace;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try{
            status = logTrace.begin("OriginController.request()");
            //target 실행
            String result = target.request(itemId);
            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

    @Override
    public String noLog() {
       return target.noLog();
    }
}
