package org.example.service;

import org.example.dto.CollectionDto;
import org.example.dto.SubCategoryDto;

import java.util.List;

public interface CollectionService {
    List<CollectionDto> getAllCollection();

    CollectionDto getCollectionById(Long id);

    boolean deleteCollectionById(Long id);

    CollectionDto getCategoryByName(String name);

    boolean saveCollection(CollectionDto collectionDto);
    CollectionDto getCollectionByName(String name);

}
