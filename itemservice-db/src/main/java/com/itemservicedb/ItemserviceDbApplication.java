package com.itemservicedb;

import com.itemservicedb.config.JdbcTemplateV2Config;
import com.itemservicedb.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

//@Import(MemoryConfig.class)
//@Import(JdbcTemplateV1Config.class)
@Slf4j
@Import(JdbcTemplateV2Config.class)
@SpringBootApplication(scanBasePackages = "com.itemservicedb.web")
public class ItemserviceDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemserviceDbApplication.class, args);
    }

    @Bean
    @Profile("local")
    public TestDataInit testDataInit(ItemRepository itemRepository) {
        return new TestDataInit(itemRepository);
    }

//    @Bean
//    @Profile("test")
//    public DataSource dataSource() {
//        log.info("메모리 데이터베이스 초기화");
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.hw.Driver");
//        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//        return dataSource;
//    }

}
