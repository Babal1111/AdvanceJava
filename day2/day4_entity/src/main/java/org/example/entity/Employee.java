package org.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}

    public Employee(String name, String email, double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
}
