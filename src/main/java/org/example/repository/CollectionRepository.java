package org.example.repository;

import org.example.entity.Collection;
import org.example.entity.SubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CollectionRepository extends CrudRepository<Collection,Long> {
    Collection getByName(String name);
}
