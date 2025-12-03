package com.berkay.ordermanagementservice.service;

import com.berkay.ordermanagementservice.dto.ProductRequest;
import com.berkay.ordermanagementservice.entity.Product;
import com.berkay.ordermanagementservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService
{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public java.util.List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequest request)
    {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        return productRepository.save(product);
    }

    public Product getProductById(Long id)
    {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product found!"));
    }

    public Product updateProduct(Long id, ProductRequest request)
    {
        Product product = getProductById(id);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id)
    {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public java.util.List<Product> searchProducts(String keyword)
    {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}