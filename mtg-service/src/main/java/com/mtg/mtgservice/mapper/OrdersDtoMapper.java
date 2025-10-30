package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.OrdersDto;
import com.mtg.mtgservice.model.Orders;
import com.mtg.mtgservice.service.OrdersService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {OrdersService.class})
public interface OrdersDtoMapper {

    Orders toEntity(OrdersDto dto) throws EntityNotFoundException;

    OrdersDto toDto(Orders orders);

    List<OrdersDto> toDtoList(List<Orders> ordersList);
}