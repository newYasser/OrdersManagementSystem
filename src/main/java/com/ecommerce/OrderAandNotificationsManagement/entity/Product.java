package com.ecommerce.OrderAandNotificationsManagement.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String serialNumber;
    @Column
    private String name;
    @Column
    private String vendor;
    @Column
    private long price;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private Category category;
    @JsonManagedReference
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}
