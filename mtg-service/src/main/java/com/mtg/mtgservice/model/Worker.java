package com.mtg.mtgservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name = "Worker")
public class Worker {
    
    @Id
    @Column(name = "EmployeeID", columnDefinition = "int", nullable = false)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Integer employeeID;

    @Column (name = "FirstName", columnDefinition = "VARCHAR(35)", nullable = false)
    String firstName;

    @Column (name = "LastName", columnDefinition = "VARCHAR(35)", nullable = false)
    String lastName;

    @Column (name = "Role", columnDefinition = "CHAR(3)", nullable = false)
    String role;

    @Column (name = "Email", columnDefinition = "VARCHAR(128)", nullable = false)
    String email;
}
