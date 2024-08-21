package org.example.repository;

import org.example.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface CategoryRepository extends CrudRepository<Category,Long> {
    Category getByName(String name);
}
