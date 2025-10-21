package com.example.demo.model.composite;

import com.example.demo.model.Orders;
import com.example.demo.model.Card;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;


@Data
@Embeddable
@NoArgsConstructor

public class OrderItemID implements Serializable {

    @JoinColumn(name = "OrderID", nullable = false)
    @ManyToOne
    Orders orderID;

    @JoinColumns({
        @JoinColumn(name = "CardNumber", referencedColumnName = "CardNumber", nullable = false),
        @JoinColumn(name = "SetName", referencedColumnName = "SetName", nullable = false)
    })
    @ManyToOne
    Card card;


}
