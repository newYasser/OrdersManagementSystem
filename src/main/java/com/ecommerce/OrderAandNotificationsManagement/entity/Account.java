package com.ecommerce.OrderAandNotificationsManagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="account_id")
    private Integer id;

    @Column
    private long balance;

    @OneToOne(mappedBy = "account")
    private Customer customer;

}
