package de.neuefische.javabooklibrary.controll;

import de.neuefische.javabooklibrary.model.Book;
import de.neuefische.javabooklibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping(path = "{isbn}")
    public Book deleteBook(@PathVariable String isbn) {
        return bookService.deleteBook(isbn);
    }

    @PutMapping("/updateBook")
    public Book updateTitleOfBook(@RequestBody Book book) {
        return bookService.updateTitleOfBook(book);
    }

    @GetMapping("/byName")
    public List<Book> getBookByName(@RequestParam String title) {
        return bookService.getBookByName(title);
    }

    @PutMapping(path = "{isbn}")
    public Book putNewBookByIsbn(@PathVariable String isbn) {
        return bookService.putNewBookByIsbn(isbn);
    }

}
