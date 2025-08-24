package com.devsocket.ecommerce.event.models.cart;

public record CartItem(
        String productId,
        int quantity,
        double price
    ) {}