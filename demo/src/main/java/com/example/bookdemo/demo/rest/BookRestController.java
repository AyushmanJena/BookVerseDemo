package com.example.bookdemo.demo.rest;


import com.example.bookdemo.demo.entity.Book;
import com.example.bookdemo.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
//@RequestMapping("/books")
public class BookRestController {
    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String findAll(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "bookverse-home";
    }

    // Show a specific book by ID in a Thymeleaf template
    @GetMapping("/book-details/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        int id = Integer.parseInt(bookId);
        Book theBook = bookService.findById(id);
        if (theBook == null) {
            throw new RuntimeException("Book not found: " + bookId);
        }
        model.addAttribute("book", theBook); // Add the book to the model
        return "book-details"; // Return the name of the Thymeleaf template (book-detail.html)
    }

    // to add new book to the database (later to be made exclusive to mods)
    @GetMapping("/addBook")
    public String showForm(Model theModel){
        theModel.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/addBook")
    public String processForm(
            @Valid @ModelAttribute("book") Book theBook, Model model) {

        theBook.setBookId(0); // Ensure this is a new book
        bookService.save(theBook);

        System.out.println(theBook.getBookId()); // log the new book

        return "redirect:/books";
    }


//    @PutMapping("/books")
//    public Book updateBook(@RequestBody Book theBook){
//        Book dbBook = bookService.save(theBook);
//        return dbBook;
//    }
//
//    @DeleteMapping("/books/{bookId}")
//    public String deleteBook(@PathVariable int bookId){
//        Book tempBook = bookService.findById(bookId);
//
//        // throw exception if null
//
//        if(tempBook == null){
//            throw new RuntimeException("Book id not found : "+ bookId);
//        }
//
//        bookService.deleteById(bookId);
//        return "Deleted Book : "+ bookId;
//    }

}
