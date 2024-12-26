package com.kata.Kata.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;
    private String title;
    private String author;
    private Integer publicationYear;
}
