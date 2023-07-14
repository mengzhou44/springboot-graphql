package com.mengzhou.graphql;

import com.mengzhou.graphql.model.Book;
import com.mengzhou.graphql.model.Review;
import com.mengzhou.graphql.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BookRepository bookRepository) {
		return args -> {

			Book reactiveSpring = new Book("Reactive Spring", 484, "Josh Long");
			Review review = new Review("Great book", "I really enjoyed this book");
			// bidirectional relationship
			review.setBook(reactiveSpring);
			reactiveSpring.setReviews(List.of(review));
			bookRepository.save(reactiveSpring);

			bookRepository.save(new Book("Spring Boot Up & Running", 328, "Mark Heckler"));
			bookRepository.save(new Book("Hacking with Spring Boot 2.3", 392, "Greg Turnquist"));
		};
	}
}
