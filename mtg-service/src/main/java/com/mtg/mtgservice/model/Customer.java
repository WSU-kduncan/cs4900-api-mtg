package com.mtg.mtgservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name = "Customer")
public class Customer {
    
    @Id
    @Column(name = "CustomerEmail", columnDefinition = "VARCHAR(128)", nullable = false)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    String customerEmail;

    @Column (name = "FirstName", columnDefinition = "VARCHAR(35)", nullable = false)
    String firstName;

    @Column (name = "LastName", columnDefinition = "VARCHAR(35)", nullable = false)
    String lastName;

    @Column (name = "PhoneNumber", columnDefinition = "CHAR(10)", nullable = false)
    String phoneNumber;
}
