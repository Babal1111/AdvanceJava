package com.example.productApp.Model;

import jakarta.validation.constraints.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Product {

    @NotBlank(message = "Name is required")
    private String name;
    private Long id;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private double price;

    @NotNull(message = "Qnty is required")
    @Min(value = 1,message = "quantity must be greater than zero")
    @Max(value = 20,message = "quantity must be Lesser than 20")
    private int quantity;

    public Product(){

    }

    public Product(String name, Long id, double price, int quantity) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
