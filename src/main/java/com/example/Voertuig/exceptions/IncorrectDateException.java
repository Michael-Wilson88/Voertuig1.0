package com.example.Voertuig.exceptions;

public class IncorrectDateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IncorrectDateException() {
        super("The end date you have entered is incorrect.");
    }

}