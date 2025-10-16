package com.mtgservice.mtg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "Card")
public class Card {

  @Id
  @Column(name = "CardNumber", nullable = false)
  Short cardNumber;

  @Id
  @Column(name = "SetName", length = 3, nullable = false)
  String setName;

  @Column(name = "CardCondition", length = 14, nullable = false)
  String cardCondition;

  @Column(name = "CardName", length = 128, nullable = false)
  String cardName;

  @Column(name = "CardDescription", columnDefinition = "text", nullable = false)
  String cardDescription;

  @Column(name = "ManaValue", length = 10, nullable = false)
  String manaValue;

  @Column(name = "CardType", length = 64, nullable = false)
  String cardType;

  @Column(name = "Price", precision = 10, scale = 2, nullable = false)
  BigDecimal price;

  @Column(name = "Stock", nullable = false)
  Integer stock;


}
