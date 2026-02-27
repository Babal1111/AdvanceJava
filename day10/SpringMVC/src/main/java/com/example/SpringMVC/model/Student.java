package com.example.SpringMVC.model;
//
//public class Student {
//    private String name;
//    private String email;
//
//    public String getName() {
//        return name;
//    }
//
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//}
//

import org.springframework.boot.autoconfigure.domain.EntityScan;
//
//import javax.annotation.processing.Generated;
//import java.lang.annotation.Repeatable;
import jakarta.persistence.*;

@Entity
@Table(name = "student_table")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // Default constructor (REQUIRED by JPA)
    public Student() {
    }

    // Constructor with fields
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Setters (recommended)
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
