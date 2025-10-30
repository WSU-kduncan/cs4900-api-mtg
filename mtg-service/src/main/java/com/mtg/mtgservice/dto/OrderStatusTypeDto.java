package com.mtg.mtgservice.dto;

import lombok.Data;

@Data
public class OrderStatusTypeDto {
  private Integer statusTypeId;        // matches entity field name
  private String statusDescription;
}
