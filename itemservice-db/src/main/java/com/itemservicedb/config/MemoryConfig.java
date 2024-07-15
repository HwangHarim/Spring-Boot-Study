package com.itemservicedb.config;

import com.itemservicedb.repository.ItemRepository;
import com.itemservicedb.repository.memory.MemoryItemRepository;
import com.itemservicedb.service.ItemService;
import com.itemservicedb.service.ItemServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryConfig {

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new MemoryItemRepository();
    }

}
