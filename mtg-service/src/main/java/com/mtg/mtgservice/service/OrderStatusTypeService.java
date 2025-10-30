package com.mtg.mtgservice.service;
import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.mtg.mtgservice.model.OrderStatusType;
import com.mtg.mtgservice.repository.OrderStatusTypeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderStatusTypeService {

  private final OrderStatusTypeRepository repo;

  public List<OrderStatusType> getAll() {
    return repo.findAll();
  }

  public List<OrderStatusType> search(String q) {
        return repo.findByStatusDescriptionContainingIgnoreCase(q);
    }
    
   public OrderStatusType getById(Integer id) {
    return repo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("OrderStatusType (" + id + ") not found"));
  }

  public OrderStatusType create(OrderStatusType e) {
    return repo.save(e);
  }

    public OrderStatusType update(Integer id, OrderStatusType incoming) {
    OrderStatusType existing = getById(id);
    existing.setStatusDescription(incoming.getStatusDescription());
    return repo.save(existing);
  }
}