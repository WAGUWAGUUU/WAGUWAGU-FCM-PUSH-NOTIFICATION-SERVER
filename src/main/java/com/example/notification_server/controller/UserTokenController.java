package com.example.notification_server.controller;


import com.example.notification_server.request.UserTokenReq;
import com.example.notification_server.service.UserTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/FcmTokens")
public class UserTokenController {

    private final UserTokenService userTokenService;

    @PostMapping("/save")
    public void save(@RequestBody UserTokenReq req) {
        System.out.println("UserTokenController fcm:"+req.fcmToken());
        System.out.println("UserTokenController userId:"+req.userId());
        userTokenService.saveToken(req.userId(), req.fcmToken());

    }
}
