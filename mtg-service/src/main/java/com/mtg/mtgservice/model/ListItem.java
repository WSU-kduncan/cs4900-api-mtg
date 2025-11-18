package com.mtg.mtgservice.model;

import com.mtg.mtgservice.model.composite.ListItemID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ListItem")
public class ListItem {

  @EmbeddedId
  ListItemID ID;

  @Column(name = "QuantityWanted", columnDefinition = "smallint", nullable = false)
  Integer quantityWanted;
}
