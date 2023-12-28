package com.ecommerce.OrderAandNotificationsManagement.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "notification_templete_id", nullable = false)
    private NotificationTemplate notificationTemplate;

}


