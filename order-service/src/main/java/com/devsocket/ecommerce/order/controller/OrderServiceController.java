package com.devsocket.ecommerce.order.controller;

import com.devsocket.ecommerce.order.dto.OrderRequest;
import com.devsocket.ecommerce.order.dto.OrderResponse;
import com.devsocket.ecommerce.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderServiceController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.placeOrder(orderRequest);

        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(new OrderRequest(orderId, null, null, null));
        return ResponseEntity.noContent().build();
    }
}
