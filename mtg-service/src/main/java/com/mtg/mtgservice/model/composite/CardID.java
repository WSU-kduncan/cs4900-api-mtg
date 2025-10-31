package com.mtg.mtgservice.model.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CardID implements Serializable {
  @Column(name = "CardNumber", columnDefinition = "SMALLINT", nullable = false)
  Integer cardNumber;

  @Column(name = "SetName", columnDefinition = "CHAR(3)", nullable = false)
  String setName;
}
