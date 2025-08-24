package com.devsocket.ecommerce.event.models.user;

public record Address(
        String street,
        String city,
        String state,
        String postalCode,
        String country
    ) {}