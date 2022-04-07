/* package de.neuefische.javabooklibrary.service;

import de.neuefische.javabooklibrary.model.Book;
import de.neuefische.javabooklibrary.repository.BookRepo;
import de.neuefische.javabooklibrary.repository.IsbnApi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    BookRepo bookRepo = mock(BookRepo.class);
    BookService bookService = new BookService(bookRepo);

    @Test
    void getAllBooks_shouldReturnAllBooks() {
        //GIVEN
        when(bookRepo.getAllBooks()).thenReturn(List.of(new Book("1234", "Unsere Welt")));
        //WHEN
        List<Book> actual = bookService.getAllBooks();
        //THEN
        List<Book> expected = new ArrayList<>(List.of(new Book("1234", "Unsere Welt")));
        verify(bookRepo).getAllBooks();
        assertEquals(expected, actual);
    }

    @Test
    void getBookByIsbn_whenAskForIsbn_shouldReturnBookWithIsbn() {
        //GIVEN
        when(bookRepo.getBookByIsbn("1234")).thenReturn(new Book("1234", "Unsere Welt"));
        //WHEN
        Book actual = bookService.getBookByIsbn("1234");
        //THEN
        Book expected = new Book("1234", "Unsere Welt");
        verify(bookRepo).getBookByIsbn("1234");
        assertEquals(expected, actual);
    }

    @Test
    void addBook_whenAddBook_shouldReturnBook() {
        //GIVEN
        when(bookRepo.addBook(new Book("1234", "Unsere Welt"))).thenReturn(new Book("1234", "Unsere Welt"));
        //WHEN
        Book actual = bookService.addBook(new Book("1234", "Unsere Welt"));
        //THEN
        Book expected = new Book("1234", "Unsere Welt");
        verify(bookRepo).addBook(new Book("1234", "Unsere Welt"));
        assertEquals(expected, actual);
    }

    @Test
    void deleteBook_whenDeleteBookWithIsbn_shouldReturnBook(){
        //GIVEN
        when(bookRepo.deleteBook("1234")).thenReturn(new Book("1234", "Unsere Welt"));
        //WHEN
        Book actual = bookService.deleteBook("1234");
        //THEN
        Book expected = new Book("1234", "Unsere Welt");
        verify(bookRepo).deleteBook("1234");
        assertEquals(expected, actual);
    }

    @Test
    void updateTitleOfBook_whenBookContainsIsbn_shouldChangeTitleAndReturnBook(){
        //GIVEN
        when(bookRepo.updateTitleOfBook(new Book("1234", "Unsere Seen"))).thenReturn(new Book("1234", "Unsere Seen"));
        //WHEN
        Book actual = bookService.updateTitleOfBook(new Book("1234", "Unsere Seen"));
        //THEN
        Book expected = new Book("1234", "Unsere Seen");
        verify(bookRepo).updateTitleOfBook(new Book("1234", "Unsere Seen"));
        assertEquals(expected, actual);
    }
}

 */