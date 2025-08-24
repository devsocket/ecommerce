package com.devsocket.ecommerce.event.models.shipping;

import java.time.Instant;

public record ShippingEvent(
    String shipmentId,
    String orderId,
    String userId,
    Address deliveryAddress,
    ShippingStatus status,
    String trackingNumber,
    String carrier,
    ShipingEventType eventType,
    Instant timestamp
) { }
