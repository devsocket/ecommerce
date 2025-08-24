package com.devsocket.ecommerce.event.models.user;

import java.time.Instant;

public record UserEvent(
    String userId,
    String email,
    String fullName,
    String phoneNumber,
    Address address,
    UserEventType eventType,
    Instant timestamp
) { }
