package com.skylimit.Skylimit.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class NotificationRequest {
    private Long productId;
    private String eventType;
    private String message;
}
