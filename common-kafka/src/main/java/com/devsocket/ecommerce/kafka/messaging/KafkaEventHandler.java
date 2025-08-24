package com.devsocket.ecommerce.kafka.messaging;

public interface KafkaEventHandler<T> {
    void handleEvent(T data);
}
