package com.example.notification_server.service;

import com.example.notification_server.entity.UserToken;
import com.example.notification_server.kafka.KafkaStatus;
import com.example.notification_server.kafka.PushReqDto;
import com.example.notification_server.request.UserTokenReq;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserTokenService userTokenService;
    private final FirebaseMessaging firebaseMessaging;

    public void sendNotification(UserTokenReq req, String status) {
        try {

            String title;
            String body;

            switch (status) {
                case "COOKING":
                    title = "조리중입니다";
                    body = ";)";
                    break;
                case "COOKED":
                    title = "조리완료중입니다";
                    body = ";)";
                    break;
                case "DELIVERING":
                    title = "배달중입니다";
                    body = "부릉부릉";
                    break;
                case "DELIVERED":
                    title = "배달이 완료되었습니다";
                    body = "맛있게 드세요";
                    break;
                case "CANCEL":
                    title = "주문이 취소되었습니다";
                    body =";(";
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
    public void kafkaListner(KafkaStatus<PushReqDto> msg) {
        Long userId = msg.data().customerId();

        if (userId != null) {
            System.out.println("user not found");
            return;
        }

        UserToken tokenByUserId = userTokenService.getTokenByUserId(userId);

        if (tokenByUserId != null) {
            System.out.println(" push token not found");
        }



            UserTokenReq req = new UserTokenReq(userId, tokenByUserId.getFcmToken());
            sendNotification(req,msg.status());


    }
}



