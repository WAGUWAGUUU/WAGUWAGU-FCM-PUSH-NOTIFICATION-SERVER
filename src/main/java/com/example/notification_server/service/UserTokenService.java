package com.example.notification_server.service;

import com.example.notification_server.exception.FcmTokenNotFoundException;
import com.example.notification_server.exception.UserNotFoundException;
import com.example.notification_server.repository.UserTokenRepository;
import com.example.notification_server.entity.UserToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserTokenService {

    private final UserTokenRepository userTokenRepository;

    public void saveToken(Long userId, String fcmToken) {

        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        UserToken existingToken = userTokenRepository.findByUserId(userId);

        if (existingToken != null) {
            // If the existing token's FCM token is null, update it
            if (existingToken.getFcmToken() == null) {
                existingToken.setFcmToken(fcmToken);
            }
            // Save the updated token
            userTokenRepository.save(existingToken);
        } else {
            // Create a new token if none exists
            UserToken token = new UserToken();
            token.setUserId(userId);
            token.setFcmToken(fcmToken);
            userTokenRepository.save(token);
        }


    }

    public UserToken getTokenByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }

        UserToken byUserId = userTokenRepository.findByUserId(userId);

        if (byUserId == null) {
            throw new UserNotFoundException(userId);
        }

        String fcm = byUserId.getFcmToken();


        if (fcm == null) {
            throw new FcmTokenNotFoundException(userId);
        }
        return byUserId;


    }
}
