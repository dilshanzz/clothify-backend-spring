package org.example.repository;


import org.example.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface ProductRepository extends CrudRepository<Product,Long> {
    Product getByName(String name);
    Product getByStocksId(long id);
}
