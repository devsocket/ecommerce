package com.devsocket.ecommerce.event.models.inventory;

import java.time.Instant;

public record InventoryEvent(
    String productId,
    int quantityChange,
    int newAvailableQuantity,
    String warehouseId,
    AdjustmentReason reason,
    InventoryEventType eventType,
    Instant timestamp
) { }
