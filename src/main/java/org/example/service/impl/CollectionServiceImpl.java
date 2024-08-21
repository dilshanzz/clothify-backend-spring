package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CollectionDto;
import org.example.entity.Collection;
import org.example.repository.CollectionRepository;
import org.example.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionRepository collectionRepository;

    @Autowired
    ObjectMapper objectMapper;
    @Override
    public boolean saveCollection(CollectionDto collectionDto) {
        Collection collection=objectMapper.convertValue(collectionDto,Collection.class);
        Collection isSaved=collectionRepository.save(collection);
        return isSaved.getId()!=null;
    }

    @Override
    public CollectionDto getCollectionByName(String name) {
        Collection collection=collectionRepository.getByName(name);
        return objectMapper.convertValue(collection,CollectionDto.class);
    }

    @Override
    public List<CollectionDto> getAllCollection() {
        Iterable<Collection> collections =collectionRepository.findAll();
        List<CollectionDto> collectionDtoList= new ArrayList<>();
        Iterator<Collection> collectionIterator =collections.iterator();
        while (collectionIterator.hasNext()){
            Collection collection=collectionIterator.next();
            CollectionDto collectionDto=objectMapper.convertValue(collection,CollectionDto.class);
            collectionDtoList.add(collectionDto);
        }
        return collectionDtoList;
    }

    @Override
    public CollectionDto getCollectionById(Long id) {
        try {
            Collection collection = collectionRepository.findById(id).get();
            if (collection.getId() == null) {
                return null;
            }
            return objectMapper.convertValue(collection, CollectionDto.class);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public boolean deleteCollectionById(Long id) {
        collectionRepository.deleteById(id);
        CollectionDto collectionDto=getCollectionById(id);
        return collectionDto == null;
    }

    @Override
    public CollectionDto getCategoryByName(String name) {
        try {
            Collection collection=collectionRepository.getByName(name);
            return objectMapper.convertValue(collection,CollectionDto.class);
        }catch (Exception exception){
            return null;
        }
    }
}
