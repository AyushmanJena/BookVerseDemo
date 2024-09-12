package com.example.bookdemo.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

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
    private String bookTitle;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_rating")
    private int bookRating;

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
    public String toString(){
        return "{Book : "+ bookTitle+", Writer : "+ bookAuthor+", Rating : "+ bookRating+" stars}";
    }
}
