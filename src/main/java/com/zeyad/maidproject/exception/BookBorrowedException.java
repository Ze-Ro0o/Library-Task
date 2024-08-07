package com.zeyad.maidproject.exception;

public class BookBorrowedException extends RuntimeException{
    public BookBorrowedException(String message) {
        super(message);
    }
}
