package com.kata.Kata.Service;

import com.kata.Kata.Dto.BookDto;
import com.kata.Kata.Exceptions.BookNotAvailableException;
import com.kata.Kata.Exceptions.BookNotFoundException;
import com.kata.Kata.Exceptions.InvalidBookException;
import com.kata.Kata.Model.Book;
import com.kata.Kata.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private Book bookMapping(BookDto book){
        Book newBook = new Book();
        newBook.setId(book.getId());
        newBook.setAuthor(book.getAuthor());
        newBook.setTitle(book.getTitle());
        newBook.setPublicationYear(book.getPublicationYear());
        newBook.setIsAvailable(true);

        return newBook;
    }

    public void addBook(BookDto book){
        if(book.getId() == null){
            return;
        }

        bookRepository.save(bookMapping(book));
    }

    public void borrowBook(String id){
        Optional<Book> book= bookRepository.findById(id);

        if(book.isEmpty()){
            throw new BookNotFoundException("Book with ID-" + id + " is not found.");
        }

        Book book1 = book.get();

        if(!book1.getIsAvailable()){
            throw new BookNotAvailableException("Book with ID-" + id + " is already borrowed.");
        }

        book1.setIsAvailable(false);
        bookRepository.save(book1);
    }

    public void returnBook(String id){
        Optional<Book> book = bookRepository.findById(id);

        if(book.isEmpty()){
            throw new BookNotFoundException("Book with ID-" + id + " is not found.");
        }

        Book book1 = book.get();

        if(book1.getIsAvailable()){
            throw new InvalidBookException("Book with ID-" + id + " was not borrowed.");
        }

        book1.setIsAvailable(true);
        bookRepository.save(book1);
    }

    public List<BookDto> viewAvailableBook(){
        List<BookDto> books = bookRepository.findByIsAvailableTrue();

        if(books.isEmpty()){
            throw new BookNotFoundException("No book found.");
        }
        return bookRepository.findByIsAvailableTrue();
    }
}
