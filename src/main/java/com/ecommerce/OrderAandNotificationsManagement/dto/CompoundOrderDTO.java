package com.ecommerce.OrderAandNotificationsManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompoundOrderDTO {
    private List<OrderDTO> compoundOrder;
}

