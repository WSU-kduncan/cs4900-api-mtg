package com.mtg.mtgservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import java.time.Instant;

@Data
@Entity
@Table(name = "Orders")

public class Orders {
    @Id    
    @Column(name = "OrderID", columnDefinition = "int", nullable = false)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    Integer orderId;

    @Column(name = "OrderDate", nullable = false)
    @CreationTimestamp(source = SourceType.DB)
    Instant orderDate;

    @JoinColumn(name = "OrderStatusTypeID", nullable = false)
    @ManyToOne
    OrderStatusType orderStatusTypeId;

    @JoinColumn(name = "CustomerEmail", nullable = false)
    @ManyToOne
    Customer customerEmail;

    @JoinColumn(name = "EmployeeID", nullable = false)
    @ManyToOne
    Worker employeeId;
}
