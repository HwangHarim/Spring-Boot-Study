package org.service.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

    private OrderServiceV0 serviceV0;

    @GetMapping("/v0/request")
    public String request(String itemId){
        serviceV0.orderItem(itemId);
        return "ok";
    }
}
