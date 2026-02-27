package com.example.SpringMVC.repository;

import com.example.SpringMVC.model.Student;
//import org.springframework.stereotype.Repository;

//@Repository
//public class StudentDao {
//    public void saveStudent(Student student){
//        System.out.println("Saved to DB: "+student.getName());
//
//    }
//}

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
}

    