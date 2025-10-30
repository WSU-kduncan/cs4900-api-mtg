package com.mtg.mtgservice.dto;

import lombok.Data;

@Data
public class ListItemDto {
  Integer listId;
  Integer cardNumber;
  String setName;
  Integer quantityWanted;
}
