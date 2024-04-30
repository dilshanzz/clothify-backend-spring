package edu.icet.controller;

import edu.icet.dto.CustomerDto;
import edu.icet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

   final CustomerService customerService;

    @PostMapping("/add")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto){
        return  customerService.create(customerDto);

    }


}
