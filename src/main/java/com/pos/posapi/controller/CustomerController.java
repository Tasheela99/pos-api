package com.pos.posapi.controller;


import com.pos.posapi.dto.CategoryDto;
import com.pos.posapi.dto.CustomerDto;
import com.pos.posapi.dto.requestdto.RequestCustomerSaveDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;
import com.pos.posapi.service.CustomerService;
import com.pos.posapi.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<StandardResponse> create(@RequestBody RequestCustomerSaveDto customerDto){
        CommonResponseDTO responseDto = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseDto.getCode(),
                        responseDto.getMessage(),
                        responseDto.getData()
                ), HttpStatus.CREATED
        );
    }
    @PutMapping(params = {"id"})
    public ResponseEntity<StandardResponse> update(
            @RequestParam (value = "id") int id,
            @RequestBody RequestCustomerSaveDto customerDto){
        CommonResponseDTO responseDto = customerService.updateCustomer(id,customerDto);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseDto.getCode(),
                        responseDto.getMessage(),
                        responseDto.getData()
                ), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<StandardResponse> delete(
            @RequestParam (value = "id") int id){
        CommonResponseDTO responseDto = customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseDto.getCode(),
                        responseDto.getMessage(),
                        responseDto.getData()
                ), HttpStatus.NO_CONTENT
        );
    }

    @GetMapping()
    public ResponseEntity<StandardResponse> get(){
        List<CustomerDto> customerDtoList = customerService.getCustomers();
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "All Customers",
                        customerDtoList
                ), HttpStatus.OK
        );
    }

    @GetMapping(path = "/by-id",params = {"id"})
    public ResponseEntity<StandardResponse> getCustomerById(
            @RequestParam(value = "id") int id
    ) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "ALl Customers of Id " + id,
                        customerDto
                ), HttpStatus.OK
        );
    }
}
