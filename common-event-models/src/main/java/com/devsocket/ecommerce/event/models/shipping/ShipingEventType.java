package com.devsocket.ecommerce.event.models.shipping;

public enum ShipingEventType {
        SHIPMENT_CREATED,
        SHIPPED,
        IN_TRANSIT,
        DELIVERED,
        DELIVERY_FAILED,
        RETURN_INITIATED
    }