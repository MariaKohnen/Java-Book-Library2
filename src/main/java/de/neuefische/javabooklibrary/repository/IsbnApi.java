package de.neuefische.javabooklibrary.repository;

import de.neuefische.javabooklibrary.model.Book;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class IsbnApi {

    private WebClient webClient;

    public IsbnApi(WebClient webClient) {
        this.webClient = webClient;
    }

    public Book retrieveTitleOfBook(String id) {

        Book correctBook = webClient.get()
                .uri("/books/"+ id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .toEntity(Book.class)
                .block()
                .getBody();

        return correctBook;
    }


}
