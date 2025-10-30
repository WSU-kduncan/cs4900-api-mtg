package com.mtg.mtgservice.model.composite;

import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.Orders;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
public class OrderItemID implements Serializable {

  @JoinColumn(name = "OrderID", nullable = false)
  @ManyToOne
  Orders orderID;

  @JoinColumns({
    @JoinColumn(name = "CardNumber", referencedColumnName = "CardNumber", nullable = false),
    @JoinColumn(name = "SetName", referencedColumnName = "SetName", nullable = false)
  })
  @ManyToOne
  Card card;
}
