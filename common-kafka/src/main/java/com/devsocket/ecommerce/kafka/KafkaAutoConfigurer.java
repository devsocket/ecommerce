package com.devsocket.ecommerce.kafka;

import com.devsocket.ecommerce.kafka.config.KafkaConfig;
import com.devsocket.ecommerce.kafka.messaging.KafkaListener;
import com.devsocket.ecommerce.kafka.messaging.KafkaProducer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        KafkaConfig.class,
        KafkaListener.class,
        KafkaProducer.class
})
public class KafkaAutoConfigurer {
}
