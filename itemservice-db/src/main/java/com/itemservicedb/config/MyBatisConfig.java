package com.itemservicedb.config;

import com.itemservicedb.repository.ItemRepository;
import com.itemservicedb.repository.mybatis.ItemMapper;
import com.itemservicedb.repository.mybatis.MyBatisItemRepository;
import com.itemservicedb.service.ItemService;
import com.itemservicedb.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final ItemMapper mapper;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new MyBatisItemRepository(mapper);
    }
}
