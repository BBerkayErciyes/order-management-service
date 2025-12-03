package com.berkay.ordermanagementservice.controller;

import com.berkay.ordermanagementservice.dto.LoginRequest;
import com.berkay.ordermanagementservice.dto.RegisterRequest;
import com.berkay.ordermanagementservice.entity.Customer;
import com.berkay.ordermanagementservice.repository.CustomerRepository;
import com.berkay.ordermanagementservice.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController
{

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, CustomerRepository customerRepository, PasswordEncoder passwordEncoder)
    {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated())
        {
            return jwtService.generateToken(request.getEmail());
        }
        else
        {
            throw new RuntimeException("User information is incorrect!");
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request)
    {
        if (customerRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new RuntimeException("This email address is already in use!");
        }

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(request.getFirstName());
        newCustomer.setLastName(request.getLastName());
        newCustomer.setEmail(request.getEmail());
        newCustomer.setPhone(request.getPhone());
        newCustomer.setPassword(passwordEncoder.encode(request.getPassword()));
        newCustomer.setRole("ROLE_USER");
        customerRepository.save(newCustomer);

        return "Registration Successful! Please log in.";
    }
}