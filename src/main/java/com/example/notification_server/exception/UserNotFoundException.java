package com.example.notification_server.exception;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException(Long userId) {
        super("User with id " + userId + " not found");
    }
}
