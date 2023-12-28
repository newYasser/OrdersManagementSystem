package com.ecommerce.OrderAandNotificationsManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @Column
    @Formula("SELECT COUNT(*) FROM product p WHERE p.category_id = id")
    private int count;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
