package org.example.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {

            // =======================
            // 1️⃣ CREATE
            // =======================
            tx = session.beginTransaction();

            Employee emp = new Employee("John Doe", "IT", 50000);
            session.save(emp);

            tx.commit();
            System.out.println("Employee Inserted!");

            int empId = emp.getId();


            // =======================
            // 2️⃣ READ
            // =======================
            Employee fetched = session.get(Employee.class, empId);
            System.out.println("Fetched: " + fetched);


            // =======================
            // 3️⃣ UPDATE
            // =======================
            tx = session.beginTransaction();

            fetched.setSalary(60000);
            session.update(fetched);

            tx.commit();
            System.out.println("Salary Updated!");


            // =======================
            // 4️⃣ DELETE
            // =======================
            tx = session.beginTransaction();

            session.delete(fetched);

            tx.commit();
            System.out.println("Employee Deleted!");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.getSessionFactory().close();
        }
    }
}