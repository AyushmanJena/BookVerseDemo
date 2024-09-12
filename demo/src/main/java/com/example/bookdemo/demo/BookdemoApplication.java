package com.example.bookdemo.demo;

import java.util.List;
// import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.bookdemo.demo.entity.Book;

@SpringBootApplication
public class BookdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookdemoApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner commandLineRunner(BookDAO bookDAO){
//		return runner -> {
//			createBooks();
//			displayBooks();
//		};
//	}
//	BookDAO bookDAO;
//
//	private void createBooks(){
//		Book book1 = new Book("How to get 1 million subs in 1 day", "Shreyas", 3);
//		Book book2 = new Book("How to use utils", "Sai", 4);
//		Book book3 = new Book("Lord of the Rizz", "Sudhansh", 1);
//
//		bookDAO.save(book1);
//		System.out.println("Saved Book id : "+book1.getId());
//		bookDAO.save(book2);
//		System.out.println("Saved Book id : "+book2.getId());
//		bookDAO.save(book3);
//		System.out.println("Saved Book id : "+book3.getId());
//	}
//
//	public void displayBooks(){
//		List<Book> allBooks = bookDAO.findAll();
//
//		for(Book theBook : allBooks){
//			System.out.println(theBook.getId() + " : " + theBook);
//		}
//	}
}
