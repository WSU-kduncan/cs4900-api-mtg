package com.mtg.mtgservice.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Orders {

    @Id
@Column(name = "OrderID", columnDefinition = "int", nullable = false)
@GeneratedValue(strategy = GenerationType.IDENTITY)   // <-- was AUTO
private Integer orderID;

    @Column(name = "OrderStatusTypeID", columnDefinition = "smallint", nullable = false)
    private Short orderStatusTypeID;

    @Column(name = "CustomerEmail", columnDefinition = "VARCHAR(128)", nullable = false)
    private String customerEmail;

    @Column(name = "EmployeeID", columnDefinition = "int", nullable = false)
    private Integer employeeID;
    
    @Column(name = "OrderDate", nullable = false, columnDefinition = "DATETIME")
private LocalDateTime orderDate = LocalDateTime.now();   // or Instant, etc.
}