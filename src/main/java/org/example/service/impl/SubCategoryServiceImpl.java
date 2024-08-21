package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.SubCategoryDto;
import org.example.entity.SubCategory;
import org.example.repository.SubCategoryRepository;
import org.example.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public boolean saveSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory subCategory =objectMapper.convertValue(subCategoryDto,SubCategory.class);
        SubCategory savedSubcategory=subCategoryRepository.save(subCategory);
        return savedSubcategory.getId() != null;
    }

    @Override
    public List<SubCategoryDto> getAllSubCategories() {
       Iterable<SubCategory> subcategories =subCategoryRepository.findAll();
       List<SubCategoryDto> subcategoriesList= new ArrayList<>();
       Iterator<SubCategory> iteratorSubcategory =subcategories.iterator();
        while (iteratorSubcategory.hasNext()){
            SubCategory subCategory=iteratorSubcategory.next();
            SubCategoryDto subCategoryDto=objectMapper.convertValue(subCategory,SubCategoryDto.class);
            subcategoriesList.add(subCategoryDto);
        }
        return subcategoriesList;
    }

    @Override
    public SubCategoryDto getSubCategoryById(Long id) {
        try {
            SubCategory subCategory = subCategoryRepository.findById(id).get();
            if (subCategory.getId() == null) {
                return null;
            }
            return objectMapper.convertValue(subCategory, SubCategoryDto.class);
        }catch (Exception exception){
            return null;
        }
    }

    @Override
    public boolean deleteSubCategoryById(Long id) {
        subCategoryRepository.deleteById(id);
       SubCategoryDto subCategoryDto=getSubCategoryById(id);
        return subCategoryDto == null;
    }

    @Override
    public SubCategoryDto getCategoryByName(String name) {
        try {
            SubCategory subCategory=subCategoryRepository.getByName(name);
            return objectMapper.convertValue(subCategory,SubCategoryDto.class);
        }catch (Exception exception){
            return null;
        }
    }
}
