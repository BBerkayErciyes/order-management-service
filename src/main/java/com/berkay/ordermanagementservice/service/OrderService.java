package com.berkay.ordermanagementservice.service;

import com.berkay.ordermanagementservice.exception.BusinessException;
import com.berkay.ordermanagementservice.entity.Customer;
import com.berkay.ordermanagementservice.entity.Order;
import com.berkay.ordermanagementservice.entity.OrderItem;
import com.berkay.ordermanagementservice.entity.Product;
import com.berkay.ordermanagementservice.enums.OrderStatus;
import com.berkay.ordermanagementservice.repository.CustomerRepository;
import com.berkay.ordermanagementservice.repository.OrderRepository;
import com.berkay.ordermanagementservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService
{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository)
    {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Order placeOrder(Long customerId, Long productId, Integer quantity)
    {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BusinessException("Customer ID not found: " + customerId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("Product ID not found: " + productId));

        if (product.getStockQuantity() < quantity)
        {
            throw new BusinessException("Insufficient Stock! Current stock: " + product.getStockQuantity());
        }
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);

        BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        order.setTotalPrice(totalPrice);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setUnitPrice(product.getPrice());
        orderItem.setOrder(order);

        List<OrderItem> items = new ArrayList<>();
        items.add(orderItem);
        order.setOrderItems(items);

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(String email)
    {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("User not found!"));

        return orderRepository.findByCustomerId(customer.getId());
    }


    public Order getOrderDetails(Long orderId, String email)
    {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BusinessException("Order not found!"));


        if (!order.getCustomer().getEmail().equals(email))
        {
            throw new BusinessException("You are not authorized to view this order!");
        }

        return order;
    }

    @Transactional
    public Order cancelOrder(Long orderId, String email)
    {
        Order order = getOrderDetails(orderId, email);

        if (order.getStatus() == OrderStatus.SHIPPED || order.getStatus() == OrderStatus.DELIVERED)
        {
            throw new BusinessException("Orders that have been shipped cannot be cancelled!");
        }

        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrderStatus(Long orderId, OrderStatus newStatus)
    {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found!"));
        order.setStatus(newStatus);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }
}