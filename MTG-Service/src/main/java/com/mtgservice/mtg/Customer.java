package com.mtgservice.mtg;

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
    @Column(name = "Customer_Email", columnDefinition = "VARCHAR(128)", nullable = false)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    String customerEmail;

    @Column (name = "First_Name", columnDefinition = "VARCHAR(35)", nullable = false)
    String firstName;

    @Column (name = "Last_Name", columnDefinition = "VARCHAR(35)", nullable = false)
    String lastName;

    @Column (name = "PhoneNumber", columnDefinition = "VARCHAR(35)", nullable = false)
    String PhoneNumber;
}