package com.mtgservice.mtg.model;

import com.mtgservice.mtg.model.composite.CardID;
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
  CardID id;

  @Column(name = "CardCondition", length = 50, nullable = false)
  String CardCondition;

  @Column(name = "CardDescription", length = 50, nullable = false)
  String CardDescription;

  @Column(name = "ManaValue", length = 10, nullable = false)
  String ManaValue;

  @Column(name = "CardType", columnDefinition = "VARCHAR(35)", nullable = false)
  String CardType;

  @Column(name = "Price", columnDefinition = "SMALLINT", nullable = false)
  Integer Price;

  @Column(name = "Stock", columnDefinition = "SMALLINT", nullable = false)
  Integer Stock;
}
