package org.service.proxy.config.v3_proxyfactory;

import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.service.proxy.trace.TraceStatus;
import org.service.proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public class LogTraceAdvice implements MethodInterceptor {

    private final LogTrace trace;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try{
            String message = invocation.getMethod().getDeclaringClass().getSimpleName()+"."+invocation.getMethod().getName()+"()";
            status = trace.begin(message);
            //target 호출
            Object result = invocation.proceed();
            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}
