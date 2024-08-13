package com.example.notification_server.kafka;

public record KafkaStatus<T>(
        T data, String status
) {

}
