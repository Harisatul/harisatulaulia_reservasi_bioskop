package org.binar.challenge_4.configuration;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class FirebaseService {

    @Value("haris-reservasi-bioskop-firebase.json")
    private String fbFile ;


    @PostConstruct
    @Bean
    FirebaseApp firebaseApp() throws IOException {
        FirebaseOptions build = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(
                        new ClassPathResource(fbFile).getInputStream()))
                .build();
        if (FirebaseApp.getApps().isEmpty())
            FirebaseApp.initializeApp(build);
        return FirebaseApp.getInstance();
    }

}
