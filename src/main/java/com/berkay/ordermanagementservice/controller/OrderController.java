package com.berkay.ordermanagementservice.controller;

import com.berkay.ordermanagementservice.dto.OrderRequest;
import com.berkay.ordermanagementservice.entity.Order;
import com.berkay.ordermanagementservice.enums.OrderStatus;
import com.berkay.ordermanagementservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController
{

    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderRequest request)
    {
        Order result = orderService.placeOrder(request.getCustomerId(), request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getMyOrders(Principal principal)
    {
        List<Order> orders = orderService.getUserOrders(principal.getName());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id, Principal principal)
    {
        Order order = orderService.getOrderDetails(id, principal.getName());
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id, Principal principal)
    {
        Order order = orderService.cancelOrder(id, principal.getName());
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status)
    {
        Order updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}