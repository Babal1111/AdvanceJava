package com.example.BookStoreRest.repository;

import com.example.BookStoreRest.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}