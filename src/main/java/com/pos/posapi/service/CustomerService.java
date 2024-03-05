package com.pos.posapi.service;



import com.pos.posapi.dto.CustomerDto;
import com.pos.posapi.dto.requestdto.RequestCustomerSaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface CustomerService {
    CommonResponseDTO createCustomer(RequestCustomerSaveDto customerDto);

    CommonResponseDTO updateCustomer(int id, RequestCustomerSaveDto customerDto);

    CommonResponseDTO deleteCustomer(int id);

    List<CustomerDto> getCustomers();

    CustomerDto getCustomerById(int id);
}
