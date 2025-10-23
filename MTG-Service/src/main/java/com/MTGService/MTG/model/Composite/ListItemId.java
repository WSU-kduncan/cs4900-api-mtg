package com.MTGService.MTG.model.Composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class ListItemId {

  @Column(name = "ListID", columnDefinition = "INT", nullable = false)
  Integer ListID;

  @Column(name = "CardNumber", columnDefinition = "SMALLINT", nullable = false)
  Integer CardNumber;

  @Column(name = "SetName", columnDefinition = "CHAR(3)", nullable = false)
  String SetName;
}
