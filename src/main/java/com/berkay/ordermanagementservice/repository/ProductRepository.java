package com.berkay.ordermanagementservice.repository;

import com.berkay.ordermanagementservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    List<Product> findByNameContainingIgnoreCase(String keyword);
}