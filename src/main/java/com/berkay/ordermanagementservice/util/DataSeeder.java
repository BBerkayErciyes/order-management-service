package com.berkay.ordermanagementservice.util;

import com.berkay.ordermanagementservice.entity.Customer;
import com.berkay.ordermanagementservice.entity.Product;
import com.berkay.ordermanagementservice.repository.CustomerRepository;
import com.berkay.ordermanagementservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner
{

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    public DataSeeder(ProductRepository productRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder)
    {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception
    {
        // NOTE: This data is for testing and development environments only.
        // It is automatically loaded for testing when the project is launched.
        if (productRepository.count() == 0)
        {
            Product p1 = new Product();
            p1.setName("Gaming Laptop");
            p1.setPrice(new BigDecimal(999));
            p1.setStockQuantity(10);
            Product p2 = new Product();
            p2.setName("Wireless Mouse");
            p2.setPrice(new BigDecimal(30));
            p2.setStockQuantity(50);
            productRepository.saveAll(Arrays.asList(p1, p2));
        }

        if (customerRepository.count() == 0)
        {
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Derek");
            customer.setEmail("customer@test.com");
            customer.setPhone("555-123-4567");
            customer.setPassword(passwordEncoder.encode("123456"));
            customer.setRole("ROLE_USER");
            customerRepository.save(customer);
            System.out.println("--- DEMO NEW CUSTOMER ---");


            Customer admin = new Customer();
            admin.setFirstName("Jack");
            admin.setLastName("Johnson");
            admin.setEmail("admin@test.com");
            admin.setPhone("555-999-9999");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            customerRepository.save(admin);
            System.out.println("--- DEMO NEW ADMIN ---");
        }
    }
}