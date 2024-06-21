package org.service.advanced.trace.hellotrace;


import org.junit.jupiter.api.Test;
import org.service.advanced.trace.TraceStatus;
import org.service.advanced.trace.hellotrace.HelloTraceV1;

public class HelloTraceV1Test {

    @Test
    public void begin() {
        HelloTraceV1 traceV1 = new HelloTraceV1();
        TraceStatus status = traceV1.begin("hello");
        traceV1.end(status);
    }

    @Test
    public void end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}