package com.shop.admin.controller;

import com.shop.admin.constant.HttpAnswer;
import com.shop.admin.exception.ExceptionHandling;
import com.shop.admin.model.HttpResponse;
import com.shop.admin.model.Notification;
import com.shop.admin.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.shop.admin.constant.HttpAnswer.NOTIFICATION_SENT_SUCCESSFULLY;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/admin/notification")
@AllArgsConstructor
@Slf4j
public class NotificationController extends ExceptionHandling {

    private final NotificationService notificationService;

    @PostMapping("/create/{username}")
    @PreAuthorize("hasAnyAuthority('user:create_notification')")
    public ResponseEntity<HttpResponse> create(@PathVariable String username,
                                               @RequestBody Notification notification){
        notificationService.create(username, notification);
        return HttpAnswer.response(OK, NOTIFICATION_SENT_SUCCESSFULLY);
    }

}
