package com.devsocket.ecommerce.userservice.dto;


import jakarta.validation.constraints.NotEmpty;

public record RegisterRequest(@NotEmpty String username, @NotEmpty String password) {}