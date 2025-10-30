package com.mtg.mtgservice.service;

import com.mtg.mtgservice.dto.OrdersDto;
import com.mtg.mtgservice.mapper.OrdersDtoMapper;
import com.mtg.mtgservice.model.Orders;
import com.mtg.mtgservice.repository.OrdersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final OrdersDtoMapper ordersDtoMapper;

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public Orders getOrderById(Integer orderID) {
        return ordersRepository.findById(orderID)
                .orElseThrow(() -> new EntityNotFoundException("Order " + orderID + " not found"));
    }

    public Orders createOrder(Orders order) {
        return ordersRepository.save(order);
    }

    @Transactional
    public Orders updateOrder(Integer orderID, OrdersDto dto) {
        Orders existing = getOrderById(orderID);          // throws if missing
        existing.setOrderStatusTypeID(dto.getOrderStatusTypeID());
        existing.setCustomerEmail(dto.getCustomerEmail());
        existing.setEmployeeID(dto.getEmployeeID());
        return ordersRepository.save(existing);
    }
}