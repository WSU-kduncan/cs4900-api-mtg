package com.mtg.mtgservice.service;


import org.springframework.stereotype.Service;

import com.mtg.mtgservice.dto.OrderStatusTypeDto;
import com.mtg.mtgservice.mapper.OrderStatusTypeDtoMapper;
import com.mtg.mtgservice.model.OrderStatusType;
import com.mtg.mtgservice.repository.OrderStatusTypeRepository;

import lombok.RequiredArgsConstructor;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


@RequiredArgsConstructor
@Service
public class OrderStatusTypeService {
    private final OrderStatusTypeRepository orderStatusTypeRepository;
    private final OrderStatusTypeDtoMapper orderStatusTypeDtoMapper;

    public List<OrderStatusType> getAllOrderStatusTypes() {
        return orderStatusTypeRepository.findAll();
    }

    public OrderStatusType getOrderStatusTypeById(Integer statusTypeID) throws EntityNotFoundException {
        return orderStatusTypeRepository
            .findById(statusTypeID)
            .orElseThrow();
    }

    public OrderStatusType updateOrderStatusType(OrderStatusType orderStatusType) {
        if (!orderStatusTypeRepository.existsById(orderStatusType.getStatusTypeID())) {
            throw new EntityNotFoundException("OrderStatusType not found");
        }
        return orderStatusTypeRepository.save(orderStatusType);
    }

    public OrderStatusType createOrderStatusType(OrderStatusTypeDto orderStatusTypeDto) throws EntityNotFoundException {
        return orderStatusTypeRepository.saveAndFlush(orderStatusTypeDtoMapper.toEntity(orderStatusTypeDto));
    }
}