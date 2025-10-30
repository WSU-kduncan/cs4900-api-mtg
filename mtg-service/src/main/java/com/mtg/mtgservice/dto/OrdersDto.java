package com.mtg.mtgservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class OrdersDto {
    Integer orderID;
    Short orderStatusTypeID;
    String customerEmail;
    Integer employeeID;
}