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
@Table(name = "OrderStatusType")
public class OrderStatusType {

  @Id
  @Column(name = "StatusTypeID", nullable = false)
  Byte statusTypeId;

  @Column(name = "StatusDescription", length = 255, nullable = false)
  String statusDescription;


}
