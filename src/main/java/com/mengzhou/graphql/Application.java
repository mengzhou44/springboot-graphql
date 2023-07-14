package com.mengzhou.graphql;

import com.mengzhou.graphql.model.Book;
import com.mengzhou.graphql.model.Product;
import com.mengzhou.graphql.model.Review;
import com.mengzhou.graphql.repository.BookRepository;
import com.mengzhou.graphql.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BookRepository bookRepository, ProductRepository productRepository) {
		return args -> {
            Product  product1 = new Product("Product 1", true, 23.4f, new BigDecimal(9.99), LocalDateTime.now());
			Product  product2 = new Product("Product 2", false, 46f,  new BigDecimal(19.99), LocalDateTime.now());
			Product  product3 = new Product("Product 3", true, 12.8f,  new BigDecimal(29.99), LocalDateTime.now());

			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);


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
