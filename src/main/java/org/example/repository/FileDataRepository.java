package org.example.repository;

import org.example.entity.ImageFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDataRepository extends CrudRepository<ImageFile,Long> {

}
