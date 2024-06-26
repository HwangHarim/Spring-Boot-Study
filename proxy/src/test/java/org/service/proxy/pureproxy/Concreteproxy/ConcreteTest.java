package org.service.proxy.pureproxy.Concreteproxy;

import org.junit.jupiter.api.Test;
import org.service.proxy.pureproxy.Concreteproxy.code.ConcreteClient;
import org.service.proxy.pureproxy.Concreteproxy.code.ConcreteLogic;
import org.service.proxy.pureproxy.Concreteproxy.code.TimeProxy;

public class ConcreteTest {

    @Test
    void noProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

    @Test
    void addProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }
}
