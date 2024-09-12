package com.example.bookdemo.demo.rest;


import com.example.bookdemo.demo.entity.Book;
import com.example.bookdemo.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {
    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> findAll(){
            return bookService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable int bookId){
        Book theBook = bookService.findById(bookId);
        if(theBook == null){
            throw new RuntimeException("Book not found : "+ bookId);
        }

        return theBook;
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book theBook){
        theBook.setBookId(0);
        Book dbBook = bookService.save(theBook);
        return dbBook;
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book theBook){
        Book dbBook = bookService.save(theBook);
        return dbBook;
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable int bookId){
        Book tempBook = bookService.findById(bookId);

        // throw exception if null

        if(tempBook == null){
            throw new RuntimeException("Book id not found : "+ bookId);
        }

        bookService.deleteById(bookId);
        return "Deleted Book : "+ bookId;
    }
}
