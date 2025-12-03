package com.berkay.ordermanagementservice.controller;

import com.berkay.ordermanagementservice.dto.CustomerUpdateRequest;
import com.berkay.ordermanagementservice.entity.Customer;
import com.berkay.ordermanagementservice.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/api/customers")
public class CustomerController
{

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping("/me")
    public ResponseEntity<Customer> getMyProfile(Principal principal)
    {
        return ResponseEntity.ok(customerService.getCustomerByEmail(principal.getName()));
    }

    @PutMapping("/me")
    public ResponseEntity<Customer> updateMyProfile(Principal principal, @RequestBody CustomerUpdateRequest request)
    {
        return ResponseEntity.ok(customerService.updateCustomer(principal.getName(), request));
    }
}