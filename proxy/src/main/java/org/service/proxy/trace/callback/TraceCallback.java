package org.service.proxy.trace.callback;

public interface TraceCallback<T> {
    T call();
}
