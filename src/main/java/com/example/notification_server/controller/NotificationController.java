package com.example.notification_server.controller;

import com.example.notification_server.service.NotificationService;
import com.example.notification_server.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private NotificationService pushNotificationService;

    @Autowired
    private UserTokenService userTokenService;

//    @PostMapping("/send")
//    public ResponseEntity<String> sendNotification(@RequestBody UserTokenReq req) {
//        UserToken userToken = userTokenService.getTokenByUserId(req.userId());
//        if (userToken != null) {
//            pushNotificationService.sendNotification(req);
//            return ResponseEntity.ok("Notification sent successfully");
//        } else {
//            return ResponseEntity.badRequest().body("User token not found");
//        }
//    }



}
