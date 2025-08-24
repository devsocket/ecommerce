package com.devsocket.ecommerce.event.models.review;

import java.time.Instant;

public record ReviewEvent(
    String reviewId,
    String productId,
    String userId,
    int rating, // Typically 1–5
    String comment,
    boolean verifiedPurchase,
    ReviewEventType eventType,
    Instant timestamp
){
}
