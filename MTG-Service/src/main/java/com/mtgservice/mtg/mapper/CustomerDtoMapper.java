package com.mtgservice.mtg.mapper;

import com.mtgservice.mtg.dto.CustomerDto;
import com.mtgservice.mtg.model.Customer;
import com.mtgservice.mtg.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {CustomerService.class})
public interface CustomerDtoMapper {

  Customer toEntity(CustomerDto customerDto) throws EntityNotFoundException;

  CustomerDto toDto(Customer customer) throws EntityNotFoundException;

  static List<CustomerDto> toDtoList(List<Customer> customerList) throws EntityNotFoundException {
    
    throw new UnsupportedOperationException("Unimplemented method 'toDtoList'");
  }
}