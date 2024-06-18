package org.service.advanced.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV0 {

    private final OrderRepositoryV0 repositoryV0;

    public void orderItem(String itemId){
        repositoryV0.save(itemId);
    }
}
