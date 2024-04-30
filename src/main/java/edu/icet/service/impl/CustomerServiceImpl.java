package edu.icet.service.impl;

import edu.icet.dto.CustomerDto;
import edu.icet.entity.CustomerEntity;
import edu.icet.repository.CustomerRepository;
import edu.icet.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

   final CustomerRepository customerRepository;

    ModelMapper mapper;

    @Bean
    public void setMapper(){
        this.mapper = new ModelMapper();
    }
    @Override
    public CustomerDto create(CustomerDto customerDto) {
        CustomerEntity customer = mapper.map(customerDto,CustomerEntity.class);
         customerRepository.save(customer) ;
         return  customerDto;
    }
}
