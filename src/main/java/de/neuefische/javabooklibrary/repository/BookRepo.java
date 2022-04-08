package de.neuefische.javabooklibrary.repository;

import de.neuefische.javabooklibrary.model.Book;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

@Repository
public class BookRepo {

    private Map<String, Book> bookRepo = new HashMap<>();
    private IsbnApi isbnApi;

    public BookRepo() {
    }

    public List<Book> getAllBooks() {
        return List.copyOf(bookRepo.values());
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        Optional<Book> bookOptional = Optional.ofNullable(bookRepo.get(isbn));
        return bookOptional;
    }

    public Book addBook(Book book) {
        Book newBook = new Book(book.getIsbn(), book.getTitle());
        bookRepo.put(newBook.getIsbn(), newBook);
        return newBook;
    }

    public Optional<Book> deleteBook(String isbn) {
        Optional<Book> book = Optional.ofNullable(bookRepo.get(isbn));
        return book;
    }


    public Book updateTitleOfBook(Book book) {
        Book existBook = bookRepo.get(book.getIsbn());
        existBook.setTitle(book.getTitle());
        return book;
    }

    public void deleteAll() {
        bookRepo = new HashMap<>();
    }

    public List<Book> getBookByName(String title) {
        List<Book> allBooks = List.copyOf(bookRepo.values());
        List<Book> bookListWithName = new ArrayList<>();
        for (Book actualBook: allBooks) {
            if (actualBook.getTitle().equals(title)) {
                bookListWithName.add(actualBook);
            }
        } return bookListWithName;
    }

    public Book addNewBookByIsbn(Book book) {
        Book newBook = new Book(book.getIsbn(), book.getTitle());
        bookRepo.put(newBook.getIsbn(), newBook);
        return newBook;
    }
}

    /*
    public Book deleteBook(String isbn) {
        Book toDeleteBook = bookRepo.get(isbn);
        bookRepo.remove(toDeleteBook);
        return toDeleteBook;
    }
     */
