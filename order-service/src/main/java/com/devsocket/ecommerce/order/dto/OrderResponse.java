package com.devsocket.ecommerce.order.dto;

import java.time.LocalDateTime;

public record OrderResponse(Long orderId, String product, Integer quantity, Double price, LocalDateTime orderDate) {
}
