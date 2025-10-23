package com.mtg.mtgservice.mapper;

import com.mtg.mtgservice.dto.CustomerDto;
import com.mtg.mtgservice.model.Customer;
import com.mtg.mtgservice.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    uses = {CustomerService.class})
public interface CustomerDtoMapper {

  Customer toEntity(CustomerDto customerDto) throws EntityNotFoundException;

  // No exception needed for a simple DTO mapping
  CustomerDto toDto(Customer customer);

  // Replaced the static implementation with an abstract method.
  // MapStruct will generate the code to loop the list and call toDto().
  List<CustomerDto> toDtoList(List<Customer> customerList);
}
