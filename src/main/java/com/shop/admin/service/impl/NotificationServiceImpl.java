package com.shop.admin.service.impl;

import com.shop.admin.model.Notification;
import com.shop.admin.model.user.User;
import com.shop.admin.repository.NotificationRepo;
import com.shop.admin.repository.UserRepo;
import com.shop.admin.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.shop.admin.constant.UserImplConstant.NO_USER_FOUND_BY_USERNAME;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;
    private final UserRepo userRepo;

    @Override
    @Transactional
    public void create(String username, Notification notification) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username));
        notification.setDateOfCreation(LocalDateTime.now());
        user.addNotification(notification);
        userRepo.save(user);
    }
}
