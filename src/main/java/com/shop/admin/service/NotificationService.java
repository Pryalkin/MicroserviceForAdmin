package com.shop.admin.service;

import com.shop.admin.model.Notification;

public interface NotificationService {
    void create(String username, Notification notification);
}
