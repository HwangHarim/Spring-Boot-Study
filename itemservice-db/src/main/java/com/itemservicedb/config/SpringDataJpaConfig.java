package com.itemservicedb.config;

import com.itemservicedb.repository.ItemRepository;
import com.itemservicedb.repository.jpa.JpaItemRepositoryV2;
import com.itemservicedb.repository.jpa.SpringDataJpaItemRepository;
import com.itemservicedb.service.ItemService;
import com.itemservicedb.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringDataJpaConfig {

    private final SpringDataJpaItemRepository springDataJpaItemRepository;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV2(springDataJpaItemRepository);
    }
}
