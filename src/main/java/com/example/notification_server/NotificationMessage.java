package com.example.notification_server;

import lombok.Data;

@Data
public class NotificationMessage {
    public String recipientToken;
    public String message;
    public String senderName;
}
