package com.mtgservice.mtg.model;

import com.mtgservice.mtg.model.composite.ListItemID;
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
  ListItemID id;

  @Column(name = "QuantityWanted", columnDefinition = "smallint", nullable = false)
  Integer quantityWanted;
}
