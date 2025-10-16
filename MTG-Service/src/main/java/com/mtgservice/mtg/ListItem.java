package com.mtgservice.mtg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "ListItem")
public class ListItem {

  @Id
  @Column(name = "ListID", nullable = false)
  Integer listId;

  @Id
  @Column(name = "CardNumber", nullable = false)
  Short cardNumber;

  @Id
  @Column(name = "SetName", length = 3, nullable = false)
  String setName;

  @Column(name = "QuantityWanted", nullable = false)
  Short quantityWanted;

  @Column(name = "date_added")
  @CreationTimestamp(source = SourceType.DB)
  Instant dateAdded;

  @Column(name = "date_last_updated")
  @UpdateTimestamp(source = SourceType.DB)
  Instant dateUpdated;
}
