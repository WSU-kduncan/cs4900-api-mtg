package com.mtg.mtgservice.service;

import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.model.Orders;
import com.mtg.mtgservice.model.composite.CardID;
import com.mtg.mtgservice.model.composite.OrderItemID;
import com.mtg.mtgservice.repository.OrderItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
  private final OrderItemRepository repo;

  public List<OrderItem> getAll() {
    return repo.findAll();
  }

  public Optional<OrderItem> getByID(Integer orderID, Integer cardNumber, String setName) {
    Orders order = new Orders();
    order.setOrderID(orderID);

    CardID cID = new CardID(cardNumber, setName);
    Card card = new Card();
    card.setID(cID);

    OrderItemID ID = new OrderItemID();
    ID.setOrderID(order);
    ID.setCard(card);

    return repo.findById(ID);
  }

  public List<OrderItem> getByOrder(Integer orderID) {
    return repo.findByIDOrderIDOrderID(orderID);
  }

  public OrderItem save(OrderItem item) {
    return repo.save(item);
  }
}
