package com.kata.Kata.Service;

import com.kata.Kata.Dto.BookDto;
import com.kata.Kata.Exceptions.BookIdAlreadyExistException;
import com.kata.Kata.Exceptions.BookNotAvailableException;
import com.kata.Kata.Exceptions.BookNotFoundException;
import com.kata.Kata.Exceptions.InvalidBookException;
import com.kata.Kata.Model.Book;
import com.kata.Kata.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
    void addBook_IdAlreadyExist() {
        BookDto inputBook = new BookDto("1", "Test Author", "Test Title", 2024);
        when(bookRepository.findById(inputBook.getId())).thenReturn(Optional.of(new Book()));

        BookIdAlreadyExistException exception = assertThrows(BookIdAlreadyExistException.class, () -> bookService.addBook(inputBook));
        assertEquals("Id already exist.", exception.getMessage());
        verify(bookRepository, times(1)).findById(inputBook.getId());
        verify(bookRepository, times(0)).save(Mockito.any(Book.class));
    }

    @Test
    void borrowBook_Borrowed() {
        String bookId = "1";
        book.setIsAvailable(true);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        bookService.borrowBook(bookId);

        assertFalse(book.getIsAvailable());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void borrowBook_BookNotFound() {
        String bookId = "1";
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.borrowBook(bookId));
        assertEquals("Book with ID-1 is not found.", exception.getMessage());
    }

    @Test
    void borrowBook_BookIsNotAvailable() {
        String bookId = "1";
        book.setIsAvailable(false);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookNotAvailableException exception = assertThrows(BookNotAvailableException.class, () -> bookService.borrowBook(bookId));
        assertEquals("Book with ID-1 is already borrowed.", exception.getMessage());
    }

    @Test
    void returnBook_BookNotFound() {
        String bookId = "1";
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.returnBook(bookId));
        assertEquals("Book with ID-1 is not found.", exception.getMessage());
    }

    @Test
    void returnBook_BookWasNotBorrowed() {
        String bookId = "1";
        book.setIsAvailable(true);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        InvalidBookException exception = assertThrows(InvalidBookException.class, () -> bookService.returnBook(bookId));
        assertEquals("Book with ID-1 was not borrowed.", exception.getMessage());
    }

    @Test
    void returnBook_Returned() {
        String bookId = "1";
        book.setIsAvailable(false);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        bookService.returnBook(bookId);

        assertTrue(book.getIsAvailable());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void viewAvailableBook_BooksAreAvailable() {
        List<BookDto> availableBooks = List.of(bookDto);
        when(bookRepository.findByIsAvailableTrue()).thenReturn(availableBooks);

        List<BookDto> result = bookService.viewAvailableBook();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(bookRepository, times(1)).findByIsAvailableTrue();
    }

    @Test
    void viewAvailableBook_NoBooksAvailable() {
        when(bookRepository.findByIsAvailableTrue()).thenReturn(List.of());

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> bookService.viewAvailableBook());
        assertEquals("No book found.", exception.getMessage());
    }
}