package com.ecommerce.OrderAandNotificationsManagement.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificationStatisticsDTO {
    private String mostNotifiedEmail;
    private String mostNotifiedPhoneNumber;
    private String mostUsedTemplete;
}
