package com.kata.Kata.Controller;

import com.kata.Kata.Dto.BookDto;
import com.kata.Kata.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<BookDto> viewAvailableBooks(){
        return new ArrayList<BookDto>(bookService.viewAvailableBook());
    }

    @PostMapping("/")
    public void addBook(@RequestBody BookDto book){
        bookService.addBook(book);
    }

    @PostMapping("/borrow/{id}")
    public void borrowBook(@PathVariable String id){
        bookService.borrowBook(id);
    }

    @PostMapping("/return/{id}")
    public void returnBook(@PathVariable String id){
        bookService.returnBook(id);
    }
}
