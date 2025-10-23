package com.mtg.mtgservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class CustomerDto {

  String customerEmail;

  String firstName;

  String lastName;

  String phoneNumber;
}
