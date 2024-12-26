package com.kata.Kata.Exceptions;

public class BookNotAvailableException extends RuntimeException{

    public BookNotAvailableException(String message){
        super(message);
    }
}
