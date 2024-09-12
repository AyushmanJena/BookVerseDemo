package com.example.bookdemo.demo.service;

import com.example.bookdemo.demo.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(int theId);

    Book save(Book theBook);

    void deleteById(int theId);
}
