package com.kata.Kata.Service;

import com.kata.Kata.Dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    public void addBook(BookDto book){

    }

    public void borrowBook(String id){

    }

    public void returnBook(String id){

    }

    public List<BookDto> viewAvailableBook(){
        return new ArrayList<>();
    }
}
