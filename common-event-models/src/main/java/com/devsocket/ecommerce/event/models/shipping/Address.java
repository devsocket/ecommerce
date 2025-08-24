package com.devsocket.ecommerce.event.models.shipping;

public record Address(
        String street,
        String city,
        String state,
        String postalCode,
        String country
    ) {}