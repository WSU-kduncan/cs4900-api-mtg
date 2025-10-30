package com.mtg.mtgservice.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemDto {
  Integer orderId;
  Integer cardNumber;
  String setName;
  Integer quantity;
  BigDecimal price;
}
