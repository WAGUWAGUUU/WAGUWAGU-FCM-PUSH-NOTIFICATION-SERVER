package com.example.notification_server.service;

import com.example.notification_server.entity.UserToken;
import com.example.notification_server.repository.UserTokenRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserTokenServiceTest {
    
    @InjectMocks
    UserTokenService userTokenService;

    @Mock
    UserTokenRepository userTokenRepository;

    private Long userId;
    private String fcmToken;

    @BeforeEach
    public void setUp() {
        userId = 1L;
        String fcmToken = "dFfephXxQl2z-kfnfiuWG5:APA91bFXd86gwVbQh7Kd2czgziaG7Nj9st7cHaGgb5rZ14XzRr_Ws2C-5Mq3aJP5eapbOkG2gccsdyWqdsPK8dLM69VIIdse-Cngs3Curerfv7ygDIWKOB05l_xz_OkpsoNHDg5h_Dm0";
          UserToken userToken = new UserToken();
          userToken.setUserId(userId);

    }


    @Test
    void  givenNullUserId_whenSaveUserToken_shouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userTokenService.saveToken(null, fcmToken);
        });
        assertEquals("userId cannot be null", exception.getMessage());

    }

    @Test
    void givenNullFcmToken_whenSaveUserToken_shouldUpdateToken() {

        UserToken userToken = new UserToken(1L,1L, null);

        userTokenService.saveToken(userId, fcmToken);

        assertEquals(fcmToken, userToken.getFcmToken());



    }


//@Transactional(db 건들때)
@Test
    void givenValidUserIdWithFcmToken_whenGetUserTokenByUserId_shouldReturnUserToken() {

        UserToken userToken = new UserToken(1L,1L, "RGG");
        when(userTokenRepository.findByUserId(1L)).thenReturn(userToken);


        UserToken token = userTokenService.getTokenByUserId(userId);

        assertEquals(1L, token.getUserId());


    }
}