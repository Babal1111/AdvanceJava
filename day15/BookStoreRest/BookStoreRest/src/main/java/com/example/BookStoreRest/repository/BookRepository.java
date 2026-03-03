package com.example.BookStoreRest.repository;

import com.example.BookStoreRest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}
