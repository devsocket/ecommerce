package com.devsocket.ecommerce.order.service;

import com.devsocket.ecommerce.event.models.order.OrderEvent;
import com.devsocket.ecommerce.kafka.messaging.KafkaEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceEventHandler implements KafkaEventHandler<Object> {
    Logger logger = LoggerFactory.getLogger(OrderServiceEventHandler.class);

    @Override
    public void handleEvent(Object data) {
        switch (data) {
            case OrderEvent event -> {
                logger.debug("Received Event Id:{} - Type: {} - data: {}",
                        event.orderId(),
                        event.orderEventType(), event);
            }
            default -> throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
