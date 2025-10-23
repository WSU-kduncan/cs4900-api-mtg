package com.mtgservice.mtg.model.composite;

import com.mtgservice.mtg.model.Card;
import com.mtgservice.mtg.model.List;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

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
