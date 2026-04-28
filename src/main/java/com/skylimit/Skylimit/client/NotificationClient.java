package com.skylimit.Skylimit.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.skylimit.Skylimit.dto.notification.NotificationRequest;
import com.skylimit.Skylimit.dto.notification.NotificationResponse;

@FeignClient(name = "notification-service", url = "http://localhost:8080/notifications")
public interface NotificationClient {

    @PostMapping
    NotificationResponse handleNotification(@RequestHeader String correlationId,
            @RequestBody NotificationRequest notificationRequest);
}
