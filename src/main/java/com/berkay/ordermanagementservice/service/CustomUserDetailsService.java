package com.berkay.ordermanagementservice.service;

import com.berkay.ordermanagementservice.entity.Customer;
import com.berkay.ordermanagementservice.repository.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(customer.getEmail(), customer.getPassword(), List.of(new SimpleGrantedAuthority(customer.getRole())));
    }
}