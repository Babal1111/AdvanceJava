package org.example.main;

import org.example.entity.Customer;
import org.example.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.Arrays;


public class MainApp {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            Customer customer = new Customer("Alice", "alice@example.com");

            Order order1 = new Order("Laptop", 1200.0, LocalDate.now());
            Order order2 = new Order("Mouse", 25.0, LocalDate.now());

            // Create list of orders
            customer.setOrder(Arrays.asList(order1, order2));

            session.beginTransaction();
            session.persist(customer);  // cascades to orders
            session.getTransaction().commit();

            System.out.println("Customer and Orders saved successfully!");
        } finally {
            session.close();
            factory.close();
        }
    }
}