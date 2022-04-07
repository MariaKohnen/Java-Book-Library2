package de.neuefische.javabooklibrary.controll;

import de.neuefische.javabooklibrary.model.Book;
import de.neuefische.javabooklibrary.repository.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private BookRepo bookRepo;

    @BeforeEach
    public void cleanUp() {
        bookRepo.deleteAll();
    }

    @Test
    void getAllBooks() {
        //GIVEN
        Book book1 = new Book ("1234", "Nice title");
        bookRepo.addBook(book1);
        //WHEN
        List<Book> actual = testClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Book.class)
                .returnResult()
                .getResponseBody();
        //THEN
        List<Book> expected = List.of(new Book("1234", "Nice title"));
        assertEquals(expected, actual);
    }

    @Test
    void getBookByIsbn() {
        //GIVEN
        Book book1 = new Book ("1234", "Nice title");
        bookRepo.addBook(book1);
        //WHEN
        Book actual = testClient.get()
                .uri("/book/1234")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        //THEN
        Book expected = new Book("1234", "Nice title");
        assertEquals(expected, actual);
    }

    @Test
    void addBook_shouldAddBook() {
        //GIVEN
        Book book = new Book ("1234", "Nice title");
        //WHEN
        Book actual = testClient.post()
                .uri("/book")
                .bodyValue(book)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        //THEN
        Book expected = new Book("1234", "Nice title");
        assertEquals(expected, actual);
    }
    @Test
    void addBook_shouldReturnError() {
        //GIVEN
        List<Book> bookList = List.of(new Book("1234", "title1"), new Book("1235", "title2"));
        //WHEN
        testClient.post()
                .uri("/book")
                .bodyValue(bookList)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void deleteBook() {
        //GIVEN
        Book book1 = new Book ("1234", "Nice title");
        bookRepo.addBook(book1);
        //WHEN
        Book actual = testClient.delete()
                .uri("/book/1234")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        //THEN
        Book expected = new Book("1234", "Nice title");
        assertEquals(expected, actual);
    }

    @Test
    void updateTitleOfBook() {
        Book book2 = new Book ("1234", "title2");
        Book book1 = new Book ("1234", "title1");
        bookRepo.addBook(book1);
        //WHEN
        Book actual = testClient.put()
                .uri("/book/updateBook")
                .bodyValue(book2)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        //THEN
        Book expected = new Book("1234", "title2");
        assertEquals(expected, actual);
    }

    @Test
    void updateTitleOfBook_whenIsbnIsWrong() {
        Book book2 = new Book ("1237", "title2");
        Book book1 = new Book ("1234", "title1");
        bookRepo.addBook(book1);
        //WHEN
        testClient.put()
                .uri("/book/updateBook")
                .bodyValue(book2)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void getBookByName() {
        //GIVEN
        Book book1 = new Book ("1234", "title1");
        bookRepo.addBook(book1);
        //WHEN
        List<Book> actual = testClient.get()
                .uri("/book/byName?title=title1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Book.class)
                .returnResult()
                .getResponseBody();
        //THEN
        List<Book> expected = List.of(new Book("1234", "title1"));
        assertEquals(expected, actual);
    }


}