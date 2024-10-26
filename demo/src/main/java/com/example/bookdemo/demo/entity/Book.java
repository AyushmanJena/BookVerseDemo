package com.example.bookdemo.demo.entity;

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

    @Column(name = "similar_1")
    private int similar1;
    @Column(name = "similar_2")
    private int similar2;
    @Column(name = "similar_3")
    private int similar3;
    @Column(name = "similar_4")
    private int similar4;
    @Column(name = "similar_5")
    private int similar5;


    public int getSimilar1() {
        return similar1;
    }

    public void setSimilar1(int similar1) {
        this.similar1 = similar1;
    }

    public int getSimilar2() {
        return similar2;
    }

    public void setSimilar2(int similar2) {
        this.similar2 = similar2;
    }

    public int getSimilar3() {
        return similar3;
    }

    public void setSimilar3(int similar3) {
        this.similar3 = similar3;
    }

    public int getSimilar4() {
        return similar4;
    }

    public void setSimilar4(int similar4) {
        this.similar4 = similar4;
    }

    public int getSimilar5() {
        return similar5;
    }

    public void setSimilar5(int similar5) {
        this.similar5 = similar5;
    }

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

    public Book(String bookTitle, String bookAuthor, int bookRating, String bookCoverLink) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookRating = bookRating;
        this.bookCoverLink = bookCoverLink;
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
