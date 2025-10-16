package com.MTGService.MTG.model;

import com.MTGService.MTG.model.Composite.CardId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "Card")
public class Card {

  @EmbeddedId
  CardId Id;

  @Column(name = "CardCondition", columnDefinition = "VARCHAR(14)", nullable = false)
  String CardCondition;

  @Column(name = "CardName", columnDefinition = "VARCHAR(128)", nullable = false)
  String CardName;

  @Column(name = "CardDescription", columnDefinition = "TEXT", nullable = false)
  String CardDescription;

  @Column(name = "ManaValue", columnDefinition = "VARCHAR(10)", nullable = false)
  String ManaValue;

  @Column(name = "CardType", columnDefinition = "VARCHAR(64)", nullable = false)
  String CardType;

  @Column(name = "Price", columnDefinition = "DECIMAL(10,2)", nullable = false)
  BigDecimal Price;

  @Column(name = "Stock", columnDefinition = "INT", nullable = false)
  Integer Stock;
}
