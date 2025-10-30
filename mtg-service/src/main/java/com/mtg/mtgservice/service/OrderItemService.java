package com.mtg.mtgservice.service;

import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.model.OrderItem;
import com.mtg.mtgservice.model.Orders;
import com.mtg.mtgservice.model.composite.CardId;
import com.mtg.mtgservice.model.composite.OrderItemID;
import com.mtg.mtgservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
  private final OrderItemRepository repo;

  public List<OrderItem> getAll() { return repo.findAll(); }

  public Optional<OrderItem> getById(Integer orderId, Integer cardNumber, String setName) {
    Orders order = new Orders();
    order.setOrderId(orderId);

    CardId cid = new CardId(cardNumber, setName);
    Card card = new Card();
    card.setId(cid);

    OrderItemID id = new OrderItemID();
    id.setOrderID(order);
    id.setCard(card);

    return repo.findById(id);
  }

  public List<OrderItem> getByOrder(Integer orderId) {
    return repo.findByIdOrderIDOrderId(orderId);
  }

  public OrderItem save(OrderItem item) { return repo.save(item); }
}
