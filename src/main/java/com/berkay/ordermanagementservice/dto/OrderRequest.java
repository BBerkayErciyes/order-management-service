package com.berkay.ordermanagementservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderRequest
{

    @NotNull(message = "Customer ID cannot be empty!")
    private Long customerId;

    @NotNull(message = "Product ID cannot be empty!")
    private Long productId;

    @NotNull(message = "Quantity cannot be zero!")
    @Min(value = 1, message = "Order quantity must be at least 1!")
    private Integer quantity;

    public OrderRequest()
    {
    }

    public OrderRequest(Long customerId, Long productId, Integer quantity)
    {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(Long productId)
    {
        this.productId = productId;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}