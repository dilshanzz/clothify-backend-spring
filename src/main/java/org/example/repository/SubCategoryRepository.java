package org.example.repository;

import org.example.entity.Category;
import org.example.entity.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory,Long> {
    SubCategory getByName(String name);
}
