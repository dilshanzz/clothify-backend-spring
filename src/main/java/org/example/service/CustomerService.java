package org.example.service;

import org.example.dto.CustomerDto;
import org.example.entity.Customer;

public interface CustomerService {
    public boolean addCustomer(CustomerDto customerDto);

    CustomerDto getCustomerByName(String name);

    Customer updateCustomer(Long id, CustomerDto customerDto);
}
