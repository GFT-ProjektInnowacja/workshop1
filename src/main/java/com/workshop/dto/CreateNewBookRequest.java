package com.workshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewBookRequest {

    private String bookName;

    private String author;

    private int numberOfPages;

    private String publisher;

}
