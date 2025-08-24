package com.devsocket.ecommerce.event.models.cart;

import java.time.Instant;
import java.util.List;

public record CartEvent(
    String cartId,
    String userId,
    List<CartItem> items,
    CartEventType eventType,
    Instant timestamp
) { }
