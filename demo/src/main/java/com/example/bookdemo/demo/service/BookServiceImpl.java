package com.example.bookdemo.demo.service;

import com.example.bookdemo.demo.dao.BookRepository;
import com.example.bookdemo.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        Book theBook = null;

        if(result.isPresent()){
            theBook = result.get();
        }

        else{
            throw new RuntimeException("Book not found" + theId);
        }

        return theBook;
    }

    @Override
    public Book save(Book theBook) {
        return bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }
}