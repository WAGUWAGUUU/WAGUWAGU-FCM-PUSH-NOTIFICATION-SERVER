package com.example.notification_server.service;

import com.example.notification_server.UserTokenRepository;
import com.example.notification_server.entity.UserToken;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserTokenService {

    private final UserTokenRepository userTokenRepository;

    public UserToken saveToken(Long userId, String fcmToken) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        UserToken existingToken = userTokenRepository.findByUserId(userId);
        if (existingToken != null) {
            existingToken.setFcmToken(fcmToken);
            return userTokenRepository.save(existingToken);
        } else {
            UserToken userToken = new UserToken();
            userToken.setUserId(userId);
            userToken.setFcmToken(fcmToken);
            return userTokenRepository.save(userToken);
        }
    }

    public UserToken getTokenByUserId(Long userId) {
        return userTokenRepository.findByUserId(userId);
    }
}
