package org.example.repository;

import jakarta.persistence.criteria.Order;
import org.example.entity.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders,Long> {

}
