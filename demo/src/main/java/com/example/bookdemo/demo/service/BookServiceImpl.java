package com.example.bookdemo.demo.service;

import com.example.bookdemo.demo.dao.BookRepository;
import com.example.bookdemo.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<Book> findAllById(Book book) {
        List<Integer> similarIds = Arrays.asList(
                book.getSimilar1(),
                book.getSimilar2(),
                book.getSimilar3(),
                book.getSimilar4(),
                book.getSimilar5()
        );

        List<Integer> validIds = new ArrayList<>();
        for (Integer id : similarIds) {
            if (id != null && id > 0) {
                validIds.add(id);
            }
        }

        if (!validIds.isEmpty()) {
            return bookRepository.findAllById(validIds);
        } else {
            return new ArrayList<>();
        }
    }
}
