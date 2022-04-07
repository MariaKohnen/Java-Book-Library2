package de.neuefische.javabooklibrary.service;

import de.neuefische.javabooklibrary.model.Book;
import de.neuefische.javabooklibrary.repository.BookRepo;
import de.neuefische.javabooklibrary.repository.IsbnApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class BookService {

    private BookRepo bookRepo;
    private IsbnApi isbnApi;

    @Autowired
    public BookService(BookRepo bookRepo, IsbnApi isbnApi) {
        this.bookRepo = bookRepo;
        this.isbnApi = isbnApi;
    }

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepo.getBookByIsbn(isbn);
    }

    public Book addBook(Book book) {
        return bookRepo.addBook(book);
    }

    public Book deleteBook(String isbn) {
        return bookRepo.deleteBook(isbn);
    }

    public Book updateTitleOfBook(Book book) {
        return bookRepo.updateTitleOfBook(book);
    }

    public List<Book> getBookByName(String title) {
        return bookRepo.getBookByName(title);
    }

    public Book putNewBookByIsbn(String id) {
        Book newBook = isbnApi.retrieveTitleOfBook(id);
        newBook.setIsbn(id);
        return bookRepo.addNewBookByIsbn(newBook);
    }
}
