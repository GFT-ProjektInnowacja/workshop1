package com.workshop.service;

import com.workshop.dto.CreateNewBookRequest;
import com.workshop.entity.Book;
import com.workshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public String createNewBook(CreateNewBookRequest request) {
        Book book = Book.builder()
                .author(request.getAuthor())
                .bookName(request.getBookName())
                .numberOfPages(request.getNumberOfPages())
                .publisher(request.getPublisher())
                .creationDate(Date.valueOf(LocalDate.now()))
                .build();

        bookRepository.save(book);

        return "Pomyślnie dodano nowa ksiazke do bazy danych!";
    }

}
