package com.example.demo.exceptions;

public class UnauthorizedAccessException extends Exception{
    public UnauthorizedAccessException() {
        super("User does not have admin privileges");
    }
}
