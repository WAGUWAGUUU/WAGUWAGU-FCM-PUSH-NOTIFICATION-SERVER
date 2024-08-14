package com.example.notification_server.exception;

public class FcmTokenNotFoundException extends IllegalArgumentException{
    public FcmTokenNotFoundException(Long userId) {
        super("FCM token not found for user for user with userId:" + userId);
    }
}
