package com.berkay.ordermanagementservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductRequest {

    @NotBlank(message = "Product name cannot be empty!")
    private String name;

    @NotNull(message = "Price cannot be empty!")
    @Min(value = 0, message = "The price must be at least 0!")
    private BigDecimal price;

    @NotNull(message = "Stock quantity cannot be empty!")
    @Min(value = 0, message = "Stock cannot be less than 0!")
    private Integer stockQuantity;

    public @NotBlank(message = "Product name cannot be empty!") String getName()
    {
        return name;
    }

    public void setName(@NotBlank(message = "Product name cannot be empty!") String name)
    {
        this.name = name;
    }

    public @NotNull(message = "Price cannot be empty!") @Min(value = 0, message = "The price must be at least 0!") BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(@NotNull(message = "Price cannot be empty!") @Min(value = 0, message = "The price must be at least 0!") BigDecimal price)
    {
        this.price = price;
    }

    public @NotNull(message = "Stock quantity cannot be empty!") @Min(value = 0, message = "Stock cannot be less than 0!") Integer getStockQuantity()
    {
        return stockQuantity;
    }

    public void setStockQuantity(@NotNull(message = "Stock quantity cannot be empty!") @Min(value = 0, message = "Stock cannot be less than 0!") Integer stockQuantity)
    {
        this.stockQuantity = stockQuantity;
    }

}