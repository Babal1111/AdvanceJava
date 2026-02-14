package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.example.entity.Student;

import java.util.List;
import java.util.Scanner;

public class App {

    static Scanner sc = new Scanner(System.in);

    public static void createStudent(SessionFactory factory) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            Student student = new Student();

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();

            student.setName(name);
            student.setAge(age);

            session.persist(student);

            tx.commit();

            System.out.println("Student Created: " + student);

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void getStudentById(SessionFactory factory) {

        Session session = factory.openSession();

        try {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            Student student = session.get(Student.class, id);

            if (student != null) {
                System.out.println("Student Found: " + student);
            } else {
                System.out.println("Student Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void getAllStudents(SessionFactory factory) {

        Session session = factory.openSession();

        try {

            List<Student> list =
                    session.createQuery("from Student", Student.class).list();

            if (list.isEmpty()) {
                System.out.println("No Students Found!");
            } else {

                System.out.println("Student List:");

                for (Student s : list) {
                    System.out.println(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void updateStudent(SessionFactory factory) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            System.out.print("Enter Student ID to Update: ");
            int id = sc.nextInt();

            Student student = session.get(Student.class, id);

            if (student == null) {
                System.out.println("Student Not Found!");
                return;
            }

            System.out.println("1. Update Name");
            System.out.println("2. Update Age");
            System.out.println("3. Update Both");

            System.out.print("Choose: ");
            int option = sc.nextInt();

            switch (option) {

                case 1:

                    System.out.print("Enter New Name: ");
                    String name = sc.next();

                    student.setName(name);
                    break;

                case 2:

                    System.out.print("Enter New Age: ");
                    int age = sc.nextInt();

                    student.setAge(age);
                    break;

                case 3:

                    System.out.print("Enter New Name: ");
                    String newName = sc.next();

                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();

                    student.setName(newName);
                    student.setAge(newAge);
                    break;

                default:
                    System.out.println("Invalid Option");
                    return;
            }

            session.merge(student);

            tx.commit();

            System.out.println("Student Updated: " + student);

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void deleteStudent(SessionFactory factory) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {

            System.out.print("Enter Student ID to Delete: ");
            int id = sc.nextInt();

            Student student = session.get(Student.class, id);

            if (student == null) {
                System.out.println("Student Not Found!");
                return;
            }

            session.remove(student);

            tx.commit();

            System.out.println("Student Deleted!");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        while (true) {

            System.out.println("\n==========");
            System.out.println("1. Create Student");
            System.out.println("2. Get Student By ID");
            System.out.println("3. Get All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createStudent(factory);
                    break;

                case 2:
                    getStudentById(factory);
                    break;

                case 3:
                    getAllStudents(factory);
                    break;

                case 4:
                    updateStudent(factory);
                    break;

                case 5:
                    deleteStudent(factory);
                    break;

                case 6:
                    factory.close();
                    System.out.println("Program Closed");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
