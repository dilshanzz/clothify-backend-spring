package org.example.service;

import org.example.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public boolean saveCategory(CategoryDto categoryDto);

    public List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String name);

    boolean deleteCategoryByName(String name);
}
