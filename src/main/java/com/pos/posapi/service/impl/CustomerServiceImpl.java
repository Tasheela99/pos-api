package com.pos.posapi.service.impl;

import com.pos.posapi.dto.CustomerDto;
import com.pos.posapi.dto.requestdto.RequestCustomerSaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.enity.Customer;
import com.pos.posapi.exception.EntryDuplicateException;
import com.pos.posapi.exception.EntryNotFoundException;
import com.pos.posapi.repo.CustomerRepo;
import com.pos.posapi.service.CustomerService;
import com.pos.posapi.util.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public CommonResponseDTO createCustomer(RequestCustomerSaveDto customerDto) {
        CustomerDto customerDto1 = new CustomerDto(
                customerDto.getCustomerName(),
                customerDto.getCustomerAddress(),
                customerDto.getCustomerMobile(),
                customerDto.isActiveState()
        );
        if (!customerRepo.existsById(customerDto1.getCustomerId())){
            customerRepo.save(customerMapper.toCustomer(customerDto1));
        }else {
            throw new EntryDuplicateException("This Customer Already Exists");
        }
        return new CommonResponseDTO(
                201,
                "CUSTOMER CREATED SUCCESSFULLY",
                customerDto1.getCustomerId(),
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO updateCustomer(int id,RequestCustomerSaveDto customerDto) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Exists");
        }

        Customer selectedCustomer = customer.get();
        selectedCustomer.setCustomerName(customerDto.getCustomerName());
        selectedCustomer.setCustomerAddress(customerDto.getCustomerAddress());
        selectedCustomer.setCustomerMobile(customerDto.getCustomerMobile());
        selectedCustomer.setActiveState(customerDto.isActiveState());
        customerRepo.save(selectedCustomer);

        return new CommonResponseDTO(
                200,
                "CUSTOMER UPDATED SUCCESSFULLY",
                selectedCustomer.getCustomerId(),
                new ArrayList<>()
        );
    }

    @Override
    public CommonResponseDTO deleteCustomer(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isEmpty()){
            throw new EntryNotFoundException("Customer Not Found");
        }
        customerRepo.delete(customer.get());
        return new CommonResponseDTO(
                204,
                "Customer DELETED SUCCESSFULLY",
                customer.get().getCustomerId(),
                new ArrayList<>()
        );
    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepo.findAll();
        if (customers.isEmpty()) {
            throw new EntryNotFoundException("No Customers Found");
        }
        return customerMapper.toCustomerDtoList(customers);
    }

    @Override
    public CustomerDto getCustomerById(int id) {
        Customer customer = customerRepo.getById(id);
        return customerMapper.toCustomerDto(customer);
    }
}
