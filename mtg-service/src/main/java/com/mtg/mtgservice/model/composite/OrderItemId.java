package com.mtg.mtgservice.model.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class OrderItemId implements Serializable {
  private static final long serialVersionUID = 1L;

  @Column(name = "OrderID", columnDefinition = "INT", nullable = false)
  Integer orderId;

  @Column(name = "CardNumber", columnDefinition = "SMALLINT", nullable = false)
  Integer cardNumber;

  @Column(name = "SetName", columnDefinition = "CHAR(3)", nullable = false)
  String setName;
}
