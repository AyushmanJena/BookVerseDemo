package com.example.bookdemo.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.bookdemo.demo.entity.Book;

@SpringBootApplication
public class BookdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookdemoApplication.class, args);
		try{
			ProcessBuilder pb = new ProcessBuilder("python3", "demo/BookRecommendation/main.py");
			Process p = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

// using jython
//	public static void main(String[] args) {
//		SpringApplication.run(BookdemoApplication.class, args);
//		System.out.println("hello");
//		PythonInterpreter interpreter = new PythonInterpreter();
//		//interpreter.execfile("demo/src/main/resources/helloWorld.py");
//		interpreter.execfile("demo/python-model/model-to-book-table.py");
//	}
}
