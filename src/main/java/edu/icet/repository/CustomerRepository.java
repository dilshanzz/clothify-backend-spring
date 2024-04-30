package edu.icet.repository;

import edu.icet.dto.CustomerDto;
import edu.icet.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface CustomerRepository extends CrudRepository<CustomerEntity,Long> {
}
