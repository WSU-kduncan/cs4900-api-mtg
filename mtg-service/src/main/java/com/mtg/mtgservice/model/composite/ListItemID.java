package com.mtg.mtgservice.model.composite;

import lombok.Data;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.List;

@Data
@Embeddable
@NoArgsConstructor

public class ListItemID implements Serializable {
    
    @JoinColumn(name = "ListID", nullable = false)
    @ManyToOne
    List listID;

    @JoinColumns({
        @JoinColumn(name = "CardNumber", referencedColumnName = "CardNumber", nullable = false),
        @JoinColumn(name = "SetName", referencedColumnName = "SetName", nullable = false)
    })
    @ManyToOne
    Card card;


}