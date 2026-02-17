package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
//    private String productName;

    private double price;

//    private LocalDate orderDate;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "order_date")
    private LocalDate orderDate;


//    @OneToOne
//    @JoinColumn(name = "customer_id", unique = true)  // foreign key to Customers
//    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)  // for bi-directional
    @JoinColumn(name = "customer_id")     // for adding person_id as foregin key
    private Customer customer;
    // Default constructor
    public Order() {}

    // Parameterized constructor
    public Order(String productName, double price, LocalDate orderDate) {
        this.productName = productName;
        this.price = price;
        this.orderDate = orderDate;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public LocalDate getOrderDate() { return orderDate; }

    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }
}
