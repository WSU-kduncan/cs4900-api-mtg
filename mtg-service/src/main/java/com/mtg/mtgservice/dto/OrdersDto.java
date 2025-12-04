package com.mtg.mtgservice.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDto {
  Integer orderID;
  Short orderStatusTypeID;
  String customerEmail;
  Integer employeeID;
  LocalDateTime orderDate;
  List<OrderItemDto> orderItems;
}
