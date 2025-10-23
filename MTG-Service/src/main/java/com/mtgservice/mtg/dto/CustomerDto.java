package com.mtgservice.mtg.dto;

import java.time.Instant;
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