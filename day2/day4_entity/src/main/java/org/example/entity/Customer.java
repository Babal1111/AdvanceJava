package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

//    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//    private Order order;

    // In Customer.java
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Order> order;

    public Customer() {}

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

//    public Order getOrder() { return order; }
public List<Order> getOrder() { return order; }

    public void setOrder(List<Order> order) {
    this.order = order;

    }
//    public void setOrder(Order order) {
//
////        this.order = order;
//        order.setCustomer(this);  // ensures FK is set
//    }

}
