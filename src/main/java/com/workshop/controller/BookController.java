package com.workshop.controller;

import com.workshop.dto.CreateNewBookRequest;
import com.workshop.entity.Book;
import com.workshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/create")
    public ResponseEntity<String> createNewBook(@RequestBody CreateNewBookRequest request) {
        String result = bookService.createNewBook(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/get/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "bookId") Long bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(book);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<Book>> getAllBooksWrittenBySpecificAuthor(@RequestParam(name = "bookAuthor") String bookAuthor) {
        List<Book> books = bookService.getAllBooksWrittenBySpecificAuthor(bookAuthor);
        return ResponseEntity.ok(books);
    }

    @PutMapping(path = "/updatePages/{newNumberOfPages}/{bookId}")
    public ResponseEntity<String> updateNumberOfPagesForSpecificBook(@PathVariable(name = "newNumberOfPages") int numberOfPages,
                                                                     @PathVariable(name = "bookId") Long bookId) {
        String result = bookService.updateNumberOfPages(numberOfPages, bookId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/delete/{bookId}")
    public ResponseEntity<String> deleteSpecificBookById(@PathVariable(name = "bookId") Long bookId) {
        String result = bookService.deleteSpecificBookById(bookId);
        return ResponseEntity.ok(result);
    }

}