package com.devsocket.ecommerce.kafka.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaListener.class);
    KafkaEventHandler<Object> handler;

    public KafkaListener(KafkaEventHandler<Object> handler) {
        this.handler = handler;
    }

    @org.springframework.kafka.annotation.KafkaListener(topicPattern = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(@Payload Object event, ConsumerRecord<String, Object> record, Acknowledgment ack) {
        try{
            handler.handleEvent(event);
            ack.acknowledge();// commit only after successful handle of message
            logger.info("processed event: {}", event);
        } catch (Exception e) {
            logger.error("Error processing event: {}\nPartition: {}\nOffset: {}\nKey: {}\nException: {}",
                    event,
                    record.partition(),
                    record.offset(),
                    record.key(),
                    e.getMessage(),
                    e
            );
            throw e;
        }
    }

}
