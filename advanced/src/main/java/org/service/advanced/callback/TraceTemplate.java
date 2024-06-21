package org.service.advanced.callback;

import org.service.advanced.trace.TraceStatus;
import org.service.advanced.trace.logtrace.LogTrace;

public class TraceTemplate {

    private final LogTrace trace;

    public TraceTemplate(LogTrace trace){
        this.trace = trace;
    }

    public <T> T execuet(String message, TraceCallback<T> callbaak){
        TraceStatus status = null;
        try {
            status = trace.begin(message);
            T result = callbaak.call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
