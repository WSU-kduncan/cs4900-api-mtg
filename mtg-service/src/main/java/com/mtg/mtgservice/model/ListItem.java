package com.mtg.mtgservice.model;

import jakarta.persistence.Entity;

import com.mtg.mtgservice.model.composite.ListItemID;

import jakarta.persistence.Column;
import lombok.Data;
import jakarta.persistence.EmbeddedId;
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
