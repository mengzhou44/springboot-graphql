package com.mengzhou.graphql.controller;

import com.mengzhou.graphql.model.Book;
import com.mengzhou.graphql.model.BookInput;
import com.mengzhou.graphql.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
	 private  BookRepository bookRepository;

	 public BookController(BookRepository bookRepository) {
	        this.bookRepository = bookRepository;
	 }
	 @QueryMapping
	 public List<Book> findAllBooks() {
		 return bookRepository.findAll();
	 }

	@QueryMapping
	public Book findBook(@Argument Integer id) {
		return bookRepository.findById(id).orElse(null);
	}

	@MutationMapping
	public Book createBook(@Argument String title, @Argument int pages, @Argument String author ) {
		 Book book = new Book(title, pages, author);
		 return bookRepository.save(book);
	}


	@MutationMapping
	public Book addBook(@Argument BookInput book) {
		Book  bookToAdd = new Book(book.title(), book.pages(), book.author());
		return bookRepository.save(bookToAdd);
	}

	@MutationMapping
	public Book deleteBook(@Argument Integer id) {
		Book bookToDelete= bookRepository.findById(id).orElse(null);
		if(bookToDelete == null) {
			throw new RuntimeException("Book not found");
		}
	    bookRepository.delete(bookToDelete);
		return bookToDelete;
	}
	@MutationMapping
	public Book updateBook(@Argument Integer id, @Argument BookInput book) {
		Book bookToUpdate = bookRepository.findById(id).orElse(null);
		if(bookToUpdate == null) {
			throw new RuntimeException("Book not found");
		}
		bookToUpdate.setTitle(book.title());
		bookToUpdate.setPages(book.pages());
		bookToUpdate.setAuthor(book.author());
		bookRepository.save(bookToUpdate);
		return bookToUpdate;
	}

	@MutationMapping
	public List<Book>  batchCreateBooks(@Argument List<BookInput> books) {
		 List<Book>  booksCreated = new ArrayList<>();
		for (BookInput book : books) {
			Book bookToCreate = new Book(book.title(), book.pages(), book.author());
			Book bookCreated = bookRepository.save(bookToCreate);
			booksCreated.add(bookCreated);
		}

		return booksCreated;
	}

}
