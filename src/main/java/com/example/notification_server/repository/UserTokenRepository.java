package com.example.notification_server.repository;

import com.example.notification_server.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    UserToken findByUserId(Long userId);
}
