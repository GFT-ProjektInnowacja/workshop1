package com.workshop.service;

import com.workshop.dto.CreateNewBookRequest;
import com.workshop.entity.Book;
import com.workshop.exceptions.BookNotFoundException;
import com.workshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Book getBookById(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);

        if (!book.isPresent()) {
            throw new BookNotFoundException("Książka o nr id: " + bookId + " nie istnieje!");
        }

        return book.get();
    }

    public List<Book> getAllBooksWrittenBySpecificAuthor(String bookAuthor) {
        List<Book> books = bookRepository.getAllBooksWrittenBySpecificUser(bookAuthor);

        if (books.isEmpty()) {
            throw new BookNotFoundException("Autor " + bookAuthor + " nie posiada żadnych książek!");
        }

        return books;
    }

    public String updateNumberOfPages(int numberOfPages, Long bookId) {
        Optional<Book> bookBeforeUpdate = bookRepository.findById(bookId);

        if (!bookBeforeUpdate.isPresent()) {
            throw new BookNotFoundException("Książka o nr id: " + bookId + " nie istnieje!");
        }

        Book updatedBook = bookBeforeUpdate.get();
        updatedBook.setNumberOfPages(numberOfPages);

        bookRepository.save(updatedBook);

        return "Pomyślnie zaktualizowano liczbę stron na " + numberOfPages;
    }

    public String deleteSpecificBookById(Long bookId) {
        Optional<Book> bookToDelete = bookRepository.findById(bookId);

        if (!bookToDelete.isPresent()) {
            throw new BookNotFoundException("Książka o nr id: " + bookId + " nie istnieje!");
        }

        bookRepository.delete(bookToDelete.get());

        return "Pomyślnie usunięto książkę o tytule " + bookToDelete.get().getBookName();
    }

}