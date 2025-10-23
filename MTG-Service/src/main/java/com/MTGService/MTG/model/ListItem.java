package com.MTGService.MTG.model;

import com.MTGService.MTG.model.Composite.ListItemId;
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
  ListItemId Id;

  @ManyToOne
  @JoinColumn(name = "ListID", referencedColumnName = "ListID", nullable = false, insertable = false, updatable = false)
  List List;

  @ManyToOne
  @JoinColumns({
    @JoinColumn(name = "CardNumber", referencedColumnName = "CardNumber", nullable = false, insertable = false, updatable = false),
    @JoinColumn(name = "SetName",   referencedColumnName = "SetName",   nullable = false, insertable = false, updatable = false)
  })
  Card Card;

  @Column(name = "QuantityWanted", columnDefinition = "SMALLINT", nullable = false)
  Integer QuantityWanted;
}
