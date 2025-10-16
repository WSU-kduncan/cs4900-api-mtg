package com.MTGService.MTG.model;

import com.MTGService.MTG.model.Composite.OrderItemId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {

  @EmbeddedId
  OrderItemId Id;

  @ManyToOne
  @JoinColumn(name = "OrderID", referencedColumnName = "OrderID", nullable = false, insertable = false, updatable = false)
  Orders Order;

  @ManyToOne
  @JoinColumns({
    @JoinColumn(name = "CardNumber", referencedColumnName = "CardNumber", nullable = false, insertable = false, updatable = false),
    @JoinColumn(name = "SetName",   referencedColumnName = "SetName",   nullable = false, insertable = false, updatable = false)
  })
  Card Card;

  @Column(name = "Quantity", columnDefinition = "SMALLINT", nullable = false)
  Integer Quantity;

  @Column(name = "Price", columnDefinition = "DECIMAL(10,2)", nullable = false)
  BigDecimal Price;
}
