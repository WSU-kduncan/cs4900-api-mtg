package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import lombok.Data;
import jakarta.persistence.EmbeddedId;
import com.example.demo.model.composite.ListItemID;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "ListItem")
public class ListItem {
    
    @EmbeddedId
    ListItemID id;

    @Column(name = "QuantityWanted", columnDefinition = "smallint", nullable = false)
    Integer quantityWanted;
}
