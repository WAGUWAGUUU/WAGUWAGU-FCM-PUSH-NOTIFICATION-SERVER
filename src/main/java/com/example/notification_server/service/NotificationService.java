package com.example.notification_server.service;

import com.example.notification_server.entity.UserToken;
import com.example.notification_server.kafka.KafkaPushReqDTO;
import com.example.notification_server.kafka.KafkaStatus;
import com.example.notification_server.kafka.PushReqDto;
import com.example.notification_server.request.UserTokenReq;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {


    private final UserTokenService userTokenService;
    private final FirebaseMessaging firebaseMessaging;

    public void sendNotification(UserTokenReq req, String status, String storeName) {
        try {

            String title;
            String body;

            switch (status) {
                case "COOKING":
                    title = storeName;
                    body = "조리중 입니다";
                    break;
                case "COOKED":
                    title = storeName;
                    body = "조리가 완료되었습니다";
                    break;
                case "DELIVERING":
                    title = storeName;
                    body = "부릉부릉";
                    break;
                case "DELIVERED":
                    title = storeName;
                    body = " 배달이 완료 되었습니다 맛있게 드세요";
                    break;
                case "CANCEL":
                    title = storeName;
                    body ="주문이 취소되었습니다;(";
                    break;

                default:
                    title = "Order Status";
                    body = "There is an update on your order.";
                    break;
            }

            Message message = Message.builder()
                    .setToken(req.fcmToken())
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .build();

            String response = firebaseMessaging.send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "order-topic")
    public void kafkaListner(KafkaStatus<KafkaPushReqDTO> msg) {
        System.out.println("kafkafkafkakffkafkafakfaf"+ msg.data().customerId() + msg);
        Long userId = msg.data().customerId();
        String storeName = msg.data().storeName();

        if (userId == null) {
            System.out.println("user not found");
            return;
        }

        UserToken tokenByUserId = userTokenService.getTokenByUserId(userId);

        if (tokenByUserId == null) {
            System.out.println(" push token not found");
        }



            UserTokenReq req = new UserTokenReq(userId, tokenByUserId.getFcmToken());
            System.out.println("kafka:"+msg);
            sendNotification(req,msg.status(),storeName);


    }
}



