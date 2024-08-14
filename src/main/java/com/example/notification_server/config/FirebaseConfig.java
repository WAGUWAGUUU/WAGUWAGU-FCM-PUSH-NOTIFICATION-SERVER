package com.example.notification_server.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        try {
            ClassPathResource resource = new ClassPathResource("firebase/notification-server-dbc87-firebase-adminsdk-to26g-500a34fc40.json");
            InputStream serviceAccount = resource.getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp app = FirebaseApp.initializeApp(options);
            return FirebaseMessaging.getInstance(app);
        } catch (FileNotFoundException e) {
            throw new IOException("Firebase configuration file not found", e);
        }
    }
}
