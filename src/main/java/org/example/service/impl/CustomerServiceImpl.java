package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ResourceNotFoundException;
import org.example.dto.CustomerDto;
import org.example.entity.Customer;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean addCustomer(CustomerDto customerDto) {
        Customer customer = mapper.convertValue(customerDto, Customer.class);
        Customer save = customerRepository.save(customer);
        return save.getId() != null;
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDto customerDto) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found in this id: " + id);
        }

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found with this id: " + id));

        BeanUtils.copyProperties(customerDto, existingCustomer, "id");

        return customerRepository.save(existingCustomer);
    }

    @Override
    public CustomerDto getCustomerByName(String name) {
        try {
            Customer customer = customerRepository.getByName(name);
            return mapper.convertValue(customer, CustomerDto.class);
        } catch (Exception exception) {
            return null;
        }
    }
}