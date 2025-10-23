package com.MTGService.MTG.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CardDto {
  Integer cardNumber;
  String setName;
  String cardName;
  String cardType;
  String manaValue;
  String cardCondition;
  String cardDescription;
  BigDecimal price;
  Integer stock;
}