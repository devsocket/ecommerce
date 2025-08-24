package com.devsocket.ecommerce.event.models.order;

import java.time.Instant;
import java.util.List;

public record OrderEvent(
    String orderId,
    String userId,
    OrderEventType orderEventType,
    Instant timestamp,
    List<OrderItem> items,
    Double totalAmount,
    String status) { }
