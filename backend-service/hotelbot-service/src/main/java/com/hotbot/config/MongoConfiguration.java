package com.hotbot.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

@Configuration

@ComponentScan({"com.hotbot"})
@EnableMongoRepositories(basePackages = {"com.hotbot.service"})
public class MongoConfiguration {
    //

    public @Bean
    MongoClient mongoClient() {
        return new MongoClient(new ServerAddress("localhost", 27017), new ArrayList<MongoCredential>() {
            {
                add(MongoCredential.createCredential(
                        "admin",
                        "hotel-bot",
                        "Gulchul123".toCharArray()));
            }
        });
    }

    public @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongoClient(), "hotel-bot");
    }

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

        mongoTemplate.setWriteConcern(WriteConcern.ACKNOWLEDGED);

        return mongoTemplate;
    }


}
