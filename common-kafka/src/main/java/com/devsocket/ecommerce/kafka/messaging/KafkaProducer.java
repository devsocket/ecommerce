package com.devsocket.ecommerce.kafka.messaging;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducer<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;
    private final String topic;

    public KafkaProducer(KafkaTemplate<String, T> kafkaTemplate, @Value("${spring.kafka.topic}") String topic){
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    public CompletableFuture<RecordMetadata> publish(T event) {
        return kafkaTemplate.send(topic, event)
                .toCompletableFuture()
                .thenApply(SendResult::getRecordMetadata);
    }
}
