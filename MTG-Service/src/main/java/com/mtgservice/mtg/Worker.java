package com.mtgservice.mtg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@Table(name = "Worker")
public class Worker {

  @Id
  @Column(name = "EmployeeID", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer EmployeeID;

  @Column(name = "FirstName", length = 35, nullable = false)
  String FirstName;

  @Column(name = "LastName", length = 35, nullable = false)
  String LastName;

  @Column(name = "Role", length = 5, nullable = false)
  String Role;

  @Column(name = "Email", length = 128, nullable = false)
  String Email;
}
