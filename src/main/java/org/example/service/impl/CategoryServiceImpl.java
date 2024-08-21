package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private  ObjectMapper objectMapper;

    @Override
    public boolean saveCategory(CategoryDto categoryDto) {
        Category category=objectMapper.convertValue(categoryDto, Category.class);
        Category savedCategory =categoryRepository.save(category);
        if (savedCategory.getId()!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
       Iterable<Category> iterableCategories=categoryRepository.findAll();
       Iterator<Category> iterateCategories=iterableCategories.iterator();
       List <CategoryDto> allCategoryDtos = new ArrayList<>();
       while (iterateCategories.hasNext()){
            Category category=iterateCategories.next();
            CategoryDto categoryDto=objectMapper.convertValue(category,CategoryDto.class);
            allCategoryDtos.add(categoryDto);
       }
       return allCategoryDtos;
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        try {
            Category category = categoryRepository.getByName(name);
            return objectMapper.convertValue(category, CategoryDto.class);
        }catch (Exception exception){
            return CategoryDto.builder().id(null).name(null).build();
        }
    }

    @Override
    public boolean deleteCategoryByName(String name) {
       CategoryDto category=getCategoryByName(name);
       categoryRepository.deleteById(category.getId());
       return true;
    }
}
