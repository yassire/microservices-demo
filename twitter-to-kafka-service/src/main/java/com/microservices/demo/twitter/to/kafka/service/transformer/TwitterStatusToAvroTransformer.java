package com.microservices.demo.twitter.to.kafka.service.transformer;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import org.springframework.stereotype.Component;
import twitter4j.Status;

@Component
public class TwitterStatusToAvroTransformer {
    public TwitterAvroModel getTwitterAvroModel(Status status) {
        return TwitterAvroModel.newBuilder()
                .setCreatedAt(status.getCreatedAt().getTime())
                .setId(status.getId())
                .setText(status.getText())
                .setUserId(status.getUser().getId())
                .build();
    }
}
