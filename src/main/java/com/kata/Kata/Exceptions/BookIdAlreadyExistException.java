package com.kata.Kata.Exceptions;

public class BookIdAlreadyExistException extends RuntimeException{
    public BookIdAlreadyExistException(String message){
        super(message);
    }
}
