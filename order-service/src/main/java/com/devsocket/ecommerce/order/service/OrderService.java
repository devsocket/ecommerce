package com.devsocket.ecommerce.order.service;

import com.devsocket.ecommerce.kafka.messaging.KafkaProducer;
import com.devsocket.ecommerce.order.dto.OrderRequest;
import com.devsocket.ecommerce.order.dto.OrderResponse;
import com.devsocket.ecommerce.order.entity.Order;
import com.devsocket.ecommerce.event.models.order.OrderEvent;
import com.devsocket.ecommerce.event.models.order.OrderEventType;
import com.devsocket.ecommerce.event.models.order.OrderItem;
import com.devsocket.ecommerce.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaProducer<OrderEvent> kafkaTemplate;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .product(orderRequest.product())
                .quantity(orderRequest.quantity())
                .price(orderRequest.price())
                .userId(orderRequest.userId())
                .build();

        Order savedOrder = orderRepository.save(order);
        kafkaTemplate.publish(createOrUpdateOrderEvent(savedOrder));

        return new OrderResponse(savedOrder.getId(), savedOrder.getProduct(), savedOrder.getQuantity(),
                savedOrder.getPrice(), savedOrder.getOrderDate());
    }

    public void deleteOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .product(orderRequest.product())
                .quantity(orderRequest.quantity())
                .price(orderRequest.price())
                .userId(orderRequest.userId())
                .build();

        orderRepository.deleteById(order.getId());
        kafkaTemplate.publish(deleteOrderEvent(order.getId()));
    }

    private OrderEvent createOrUpdateOrderEvent(Order order) {
        return new OrderEvent(order.getId().toString(), order.getUserId().toString(),
                OrderEventType.CREATED, order.getOrderDate() == null ? Instant.now() : order.getOrderDate().atZone(ZoneId.systemDefault()).toInstant(),
                List.of(new OrderItem(order.getProduct(), order.getQuantity(), order.getPrice())), order.getPrice(), "accepted");
    }

    private OrderEvent deleteOrderEvent(Long id) {
        return new OrderEvent(id.toString(), null, OrderEventType.CANCELLED, null, null,null, null);
    }


}
