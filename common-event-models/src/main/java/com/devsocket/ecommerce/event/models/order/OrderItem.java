package com.devsocket.ecommerce.event.models.order;

public record OrderItem(
        String productId,
        int quantity,
        double price
    ) {}