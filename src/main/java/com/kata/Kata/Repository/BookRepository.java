package com.kata.Kata.Repository;

import com.kata.Kata.Dto.BookDto;
import com.kata.Kata.Model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<BookDto> findByIsAvailableTrue();
}
