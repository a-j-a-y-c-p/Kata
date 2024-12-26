package com.kata.Kata.Service;

import com.kata.Kata.Dto.BookDto;
import com.kata.Kata.Model.Book;
import com.kata.Kata.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private BookDto bookDto;

    @BeforeEach
    void testData(){
        bookDto = new BookDto("1", "Test Title", "Test Author", 2024);
        book = new Book("1", "Test Title", "Test Author", 2024, true);
    }

    @Test
    void addBook() {
    }

    @Test
    void borrowBook() {
    }

    @Test
    void returnBook() {
    }

    @Test
    void viewAvailableBook() {
    }
}