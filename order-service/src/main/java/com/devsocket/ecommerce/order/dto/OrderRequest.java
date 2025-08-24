package com.devsocket.ecommerce.order.dto;

public record OrderRequest(Long userId, String product, Integer quantity, Double price) { }
