package com.example.Voertuig.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String userName) {
        super("User " + userName + " does not exist.");
    }
}
