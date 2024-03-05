package com.pos.posapi.util.mapper;


import com.pos.posapi.dto.CustomerDto;
import com.pos.posapi.enity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDto customerDto1);

    List<CustomerDto> toCustomerDtoList(List<Customer> customers);

    CustomerDto toCustomerDto(Customer customer);
}
