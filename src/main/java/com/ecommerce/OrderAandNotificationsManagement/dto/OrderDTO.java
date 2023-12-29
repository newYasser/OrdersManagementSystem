package com.ecommerce.OrderAandNotificationsManagement.dto;

import com.ecommerce.OrderAandNotificationsManagement.entity.OrderDetail;
import lombok.*;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Integer customer_id;
    private List<OrderDetailDTO> orderDetails;

}
