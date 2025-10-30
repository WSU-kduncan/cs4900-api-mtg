package com.mtg.mtgservice.service;

import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.ListItem;
import com.mtg.mtgservice.model.composite.CardId;
import com.mtg.mtgservice.model.composite.ListItemID;
import com.mtg.mtgservice.repository.ListItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListItemService {
  private final ListItemRepository repo;

  public List<ListItem> getAll() {
    return repo.findAll();
  }

  public Optional<ListItem> getById(Integer listId, Integer cardNumber, String setName) {
    com.mtg.mtgservice.model.Lists listEntity = new com.mtg.mtgservice.model.Lists();
    listEntity.setListID(listId);

    CardId cid = new CardId(cardNumber, setName);
    Card card = new Card();
    card.setId(cid);

    ListItemID id = new ListItemID();
    id.setListID(listEntity);
    id.setCard(card);

    return repo.findById(id);
  }

  public List<ListItem> getByList(Integer listId) {
    return repo.findByIdListIDListID(listId);
  }

  public ListItem save(ListItem item) {
    return repo.save(item);
  }
}
