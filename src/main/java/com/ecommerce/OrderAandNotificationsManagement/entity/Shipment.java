package com.ecommerce.OrderAandNotificationsManagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "shipments")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderEntity> orders;;
}
