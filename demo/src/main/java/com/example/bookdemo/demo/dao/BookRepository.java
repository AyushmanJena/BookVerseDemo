package com.example.bookdemo.demo.dao;

import com.example.bookdemo.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
