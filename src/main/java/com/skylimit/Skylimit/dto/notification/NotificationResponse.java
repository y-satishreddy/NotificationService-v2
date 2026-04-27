package com.skylimit.Skylimit.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NotificationResponse {
private String eventType;
private String message;
}
