package com.devsocket.ecommerce.event.models.payment;

import java.time.Instant;

public record PaymentEvent(
    String paymentId,
    String orderId,
    String userId,
    double amount,
    String currency,
    PaymentMethod method,
    PaymentStatus status,
    PaymentEventType eventType,
    Instant timestamp
) { }
