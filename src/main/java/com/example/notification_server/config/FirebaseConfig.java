package com.example.notification_server.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


import java.io.IOException;
import java.util.List;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
     if (FirebaseApp.getApps().isEmpty()) {GoogleCredentials googleCredentials = GoogleCredentials
             .fromStream(new ClassPathResource("firebase/notification-server-dbc87-firebase-adminsdk-to26g-ca648d3799.json").getInputStream());
         FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                 .setCredentials(googleCredentials).build();
          FirebaseApp.initializeApp(firebaseOptions);}

         return FirebaseMessaging.getInstance();
    }
}
