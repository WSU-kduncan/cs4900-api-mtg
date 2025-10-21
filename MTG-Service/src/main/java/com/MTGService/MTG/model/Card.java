package com.MTGService.MTG.model;

import com.MTGService.MTG.model.Composite.CardId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Card")
public class Card {

  @EmbeddedId
  CardId id;

  @Column(name = "CardCondition", columnDefinition = "VARCHAR(14)", nullable = false)
  String cardCondition;

  @Column(name = "CardName", columnDefinition = "VARCHAR(128)", nullable = false)
  String cardName;

  @Column(name = "CardDescription", columnDefinition = "TEXT", nullable = false)
  String cardDescription;

  @Column(name = "ManaValue", columnDefinition = "VARCHAR(10)", nullable = false)
  String manaValue;

  @Column(name = "CardType", columnDefinition = "VARCHAR(64)", nullable = false)
  String cardType;

  @Column(name = "Price", columnDefinition = "DECIMAL(10,2)", nullable = false)
  java.math.BigDecimal price;

  @Column(name = "Stock", columnDefinition = "INT", nullable = false)
  Integer stock;
}
