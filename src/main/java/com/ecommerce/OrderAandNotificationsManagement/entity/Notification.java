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
    @Column
    private String subject;
    @Column
    private String message;
}
