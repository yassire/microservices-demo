package com.microservices.demo.twitter.to.kafka.service;

import com.microservices.demo.twitter.to.kafka.service.init.impl.KafkaStreamInitializer;
import com.microservices.demo.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import twitter4j.TwitterException;


@SpringBootApplication
@ComponentScan(basePackages = "com.microservices.demo")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    private final KafkaStreamInitializer kafkaStreamInitializer;

    private final StreamRunner streamRunner;

    public TwitterToKafkaServiceApplication(KafkaStreamInitializer kafkaStreamInitializer, StreamRunner runner) {
        this.kafkaStreamInitializer = kafkaStreamInitializer;
        this.streamRunner = runner;
    }
    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws TwitterException
    {
        LOG.info("Application starts ...");
        kafkaStreamInitializer.init();
        streamRunner.start();
    }
}
