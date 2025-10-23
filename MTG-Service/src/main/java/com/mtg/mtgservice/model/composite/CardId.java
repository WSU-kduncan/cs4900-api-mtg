package com.mtg.mtgservice.model.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CardId implements Serializable {
  @Column(name = "CardNumber", columnDefinition = "SMALLINT", nullable = false)
  Integer cardNumber;

  @Column(name = "SetName", columnDefinition = "CHAR(3)", nullable = false)
  String setName;
}
