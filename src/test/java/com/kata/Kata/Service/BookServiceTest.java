package com.kata.Kata.Service;

import com.kata.Kata.Dto.BookDto;
import com.kata.Kata.Exceptions.InvalidBookException;
import com.kata.Kata.Model.Book;
import com.kata.Kata.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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
    void addBook_IdIsNotNull() {
        when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);

        bookService.addBook(bookDto);

        verify(bookRepository, times(1)).save(Mockito.any(Book.class));
    }

    @Test
    void addBook_IdIsNull() {
        BookDto invalidBookDto = new BookDto(null, "Test Author", "Test Title", 2024);

        InvalidBookException exception = assertThrows(InvalidBookException.class, () -> bookService.addBook(invalidBookDto));
        assertEquals("Invalid book details.", exception.getMessage());
        verify(bookRepository, times(0)).save(Mockito.any(Book.class));
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