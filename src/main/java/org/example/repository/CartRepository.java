package org.example.repository;


import org.example.dto.CartDto;
import org.example.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {
    @Query(value = "SELECT * FROM cart c WHERE c.customer_id = ?1", nativeQuery = true)
    List<Cart> findAllByCustomerId(Long customerId);
}
