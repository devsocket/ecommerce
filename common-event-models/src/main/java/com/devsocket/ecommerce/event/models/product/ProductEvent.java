package com.devsocket.ecommerce.event.models.product;

import java.time.Instant;
import java.util.List;

public record ProductEvent(
    Long productId,
    String name,
    String description,
    double price,
    int availableQuantity,
    List<Category> categories,
    ProductEventType eventType,
    Instant timestamp
) { }

