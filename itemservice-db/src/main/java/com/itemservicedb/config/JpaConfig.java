package com.itemservicedb.config;

import com.itemservicedb.repository.ItemRepository;
import com.itemservicedb.repository.jpa.JpaItemRepository;
import com.itemservicedb.service.ItemService;
import com.itemservicedb.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {

    private final EntityManager manager;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepository(manager);
    }
}
