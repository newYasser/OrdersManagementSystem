package com.ecommerce.OrderAandNotificationsManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "notification_template")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String subject;
    @Column
    private String message;

    @OneToMany(mappedBy = "notificationTemplate")
    private List<Notification> notifications;
}
