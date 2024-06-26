package org.service.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;
import org.service.proxy.pureproxy.decorator.code.*;

public class DecoratorPattenTest {

    @Test
    void noDecorator(){
        Component component = new RealComponent();
        DecoratorClient client = new DecoratorClient(component);
        client.execute();

    }

    @Test
    void decorator1(){
        Component realComponent = new RealComponent();
        MessageDecorator messageDecorator = new MessageDecorator(realComponent);
        DecoratorClient client = new DecoratorClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2(){
        Component realComponent = new RealComponent();
        TimeDecorator timeDecorator = new TimeDecorator(realComponent);
        MessageDecorator messageDecorator = new MessageDecorator(timeDecorator);
        DecoratorClient client = new DecoratorClient(messageDecorator);
        client.execute();
    }
}
