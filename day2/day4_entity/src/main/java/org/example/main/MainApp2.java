// this is for department and employee ie MANY to ONE

package org.example.main;

import org.example.entity.Department;
import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Arrays;

public class MainApp2 {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {
            // Create Department
            Department dept = new Department("IT");

            // Create Employees
            Employee e1 = new Employee("Alice", "alice@company.com", 1200);
            Employee e2 = new Employee("Bob", "bob@company.com", 1000);

            // Link employees to department
            dept.setEmployees(Arrays.asList(e1, e2));

            // Persist department (cascades to employees)
            session.beginTransaction();
            session.persist(dept);
            session.getTransaction().commit();

            System.out.println("Department and employees saved successfully!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
