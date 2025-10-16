package com.mtgservice.mtg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "List")
public class List {

  @Id
  @Column(name = "ListID", nullable = false)
  Integer listId;

  @Column(name = "CustomerEmail", length = 128, nullable = false)
  String customerEmail;

  @Column(name = "ListName", length = 32, nullable = false)
  String listName;
 
    @JoinColumn(name = "CustomerEmail", nullable = false, insertable = false, updatable = false)
    @ManyToOne
        Customer customer;
}
