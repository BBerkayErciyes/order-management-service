package com.berkay.ordermanagementservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


@Entity
@Table(name="products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public int getStockQuantity()
    {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity)
    {
        this.stockQuantity = stockQuantity;
    }
}
