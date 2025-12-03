package com.berkay.ordermanagementservice.service;

import com.berkay.ordermanagementservice.dto.CustomerUpdateRequest;
import com.berkay.ordermanagementservice.entity.Customer;
import com.berkay.ordermanagementservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByEmail(String email)
    {
        return customerRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public Customer updateCustomer(String email, CustomerUpdateRequest request)
    {
        Customer customer = getCustomerByEmail(email);
        if(request.getFirstName() != null)
            customer.setFirstName(request.getFirstName());

        if(request.getLastName() != null)
            customer.setLastName(request.getLastName());

        if(request.getPhone() != null)
            customer.setPhone(request.getPhone());

        return customerRepository.save(customer);
    }
}