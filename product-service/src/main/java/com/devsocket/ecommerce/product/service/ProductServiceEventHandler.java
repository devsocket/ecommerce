package com.devsocket.ecommerce.product.service;

import com.devsocket.ecommerce.event.models.order.OrderEvent;
import com.devsocket.ecommerce.event.models.product.ProductEvent;
import com.devsocket.ecommerce.kafka.messaging.KafkaEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceEventHandler implements KafkaEventHandler<Object> {
    Logger logger = LoggerFactory.getLogger(ProductServiceEventHandler.class);

    @Override
    public void handleEvent(Object data) {
        switch (data) {
            case ProductEvent event -> {
                logger.debug("Received Event Id:{} - Type: {} - data: {}",
                        event.productId(),
                        event.productId(), event);
            }
            default -> throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
