package com.example.SpringMVC.service;

import com.example.SpringMVC.model.Student;
import com.example.SpringMVC.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public void saveStudent(Student student){
        studentDao.save(student);
    }
}
