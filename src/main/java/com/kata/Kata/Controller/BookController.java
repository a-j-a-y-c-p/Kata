package com.kata.Kata.Controller;

import com.kata.Kata.Dto.BookDto;
import com.kata.Kata.Exceptions.BookNotAvailableException;
import com.kata.Kata.Exceptions.BookNotFoundException;
import com.kata.Kata.Exceptions.InvalidBookException;
import com.kata.Kata.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> viewAvailableBooks(){
        try{
            return new ResponseEntity<List<BookDto>>(bookService.viewAvailableBook(), HttpStatus.OK);
        }
        catch (BookNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> addBook(@RequestBody BookDto book){
        try {
            bookService.addBook(book);
            return new ResponseEntity<String>("Book added successfully",HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Error adding book: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<String> borrowBook(@PathVariable String id){
        try {
            bookService.borrowBook(id);
            return new ResponseEntity<String>("Book borrowed successfully",HttpStatus.OK);
        } catch (BookNotFoundException e) {
            return new ResponseEntity<String>("Error: " + e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (BookNotAvailableException e) {
            return new ResponseEntity<String>("Error: " + e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error borrowing book: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<String> returnBook(@PathVariable String id){
        try{
            bookService.returnBook(id);
            return new ResponseEntity<String>("Book returned successfully",HttpStatus.OK);
        }
        catch (BookNotFoundException e){
            return new ResponseEntity<String>("Error: " + e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (InvalidBookException e){
            return new ResponseEntity<String>("Error: " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<String>("Error returning book: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
