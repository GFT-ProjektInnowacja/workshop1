package com.workshop.controller;

import com.workshop.dto.CreateNewBookRequest;
import com.workshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/books/create")
    public ResponseEntity<String> createNewBook(@RequestBody CreateNewBookRequest request) {
        String result = bookService.createNewBook(request);
        return ResponseEntity.ok(result);
    }

}