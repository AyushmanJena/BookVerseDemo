package com.example.bookdemo.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "book")
public class Book {
    //defining fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonProperty("bookId")
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_title")
    @NotBlank(message = "Author Name is required")
    private String bookTitle;

    @Column(name = "book_author")
    @NotBlank(message = "Author Name is required, 'Anonymous' if unknown")
    private String bookAuthor;

    @Column(name = "book_rating")
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating cannot be less than 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private int bookRating;

    public String getBookCoverLink() {
        return bookCoverLink;
    }

    public void setBookCoverLink(String bookCoverLink) {
        this.bookCoverLink = bookCoverLink;
    }

    @Column(name = "book_cover_link")
    private String bookCoverLink;

    public Book(){

    }

    public Book(String bookTitle, String bookAuthor, int bookRating){
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookRating = bookRating;
    }

    public int getId(){
        return bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle(){
        return bookTitle;
    }

    public String getBookAuthor(){
        return bookAuthor;
    }

    public int getBookRating(){
        return bookRating;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookRating(int value){
        this.bookRating = value;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookRating=" + bookRating +
                ", bookCoverLink='" + bookCoverLink + '\'' +
                '}';
    }
}
