package com.mtg.mtgservice.model;

import com.mtg.mtgservice.model.composite.ListItemId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ListItem")
public class ListItem {

  @EmbeddedId
  ListItemId id;

  @ManyToOne
  @JoinColumn(
      name = "ListID",
      referencedColumnName = "ListID",
      nullable = false,
      insertable = false,
      updatable = false)
  List list;

  @ManyToOne
  @JoinColumns({
    @JoinColumn(
        name = "CardNumber",
        referencedColumnName = "CardNumber",
        nullable = false,
        insertable = false,
        updatable = false),
    @JoinColumn(
        name = "SetName",
        referencedColumnName = "SetName",
        nullable = false,
        insertable = false,
        updatable = false)
  })
  Card card;

  @Column(name = "QuantityWanted", columnDefinition = "SMALLINT", nullable = false)
  Integer quantityWanted;
}
