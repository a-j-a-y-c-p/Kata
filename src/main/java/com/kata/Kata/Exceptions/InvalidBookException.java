package com.kata.Kata.Exceptions;

public class InvalidBookException extends RuntimeException{

    public InvalidBookException(String message){
        super(message);
    }
}
