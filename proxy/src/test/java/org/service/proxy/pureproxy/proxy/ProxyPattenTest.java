package org.service.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import org.service.proxy.pureproxy.proxy.code.CacheProxy;
import org.service.proxy.pureproxy.proxy.code.ProxyPattenClient;
import org.service.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPattenTest {

    @Test
    void noProxyTest(){
        RealSubject realSubject = new RealSubject();
        ProxyPattenClient client = new ProxyPattenClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest(){
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPattenClient client = new ProxyPattenClient(cacheProxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
