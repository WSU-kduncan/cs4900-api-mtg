package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderStatusType")

public class OrderStatusType {
    @Id
    @Column (name = "StatusTypeID",columnDefinition = "tinyint", nullable = false)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Integer statusTypeId;

    @Column (name = "StatusDescription", columnDefinition = "VARCHAR(255)", nullable = false)
    String statusDescription;
}
