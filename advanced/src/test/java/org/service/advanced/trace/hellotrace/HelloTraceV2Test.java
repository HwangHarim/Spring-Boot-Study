package org.service.advanced.trace.hellotrace;


import org.junit.jupiter.api.Test;
import org.service.advanced.trace.TraceStatus;
import org.service.advanced.trace.hellotrace.HelloTraceV2;

public class HelloTraceV2Test {

    @Test
    public void begin() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus status1 = traceV2.begin("hello1");
        TraceStatus status2 = traceV2.beginSync(status1.getTraceId(), "hello2");
        traceV2.end(status1);
        traceV2.end(status2);
    }

    @Test
    public void end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}