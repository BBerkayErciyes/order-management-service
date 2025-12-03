package com.berkay.ordermanagementservice.repository;

import com.berkay.ordermanagementservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>
{
}
