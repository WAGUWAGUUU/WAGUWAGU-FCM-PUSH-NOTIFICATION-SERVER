package com.example.notification_server.request;



import com.example.notification_server.entity.UserToken;


public record UserTokenReq(
         Long userId, String fcmToken
) {
    public UserToken toUserToken() {
        return UserToken.builder()
                .userId(userId)
                .fcmToken(fcmToken)
                .build();
    }



}
