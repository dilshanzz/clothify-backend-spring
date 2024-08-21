package org.example.service;

import jakarta.validation.Valid;
import org.example.dto.SubCategoryDto;

import java.util.List;

public interface SubCategoryService {
    public boolean saveSubCategory(@Valid SubCategoryDto SubCategoryDto);

    public List<SubCategoryDto> getAllSubCategories();

    SubCategoryDto getSubCategoryById(Long id);

    boolean deleteSubCategoryById(Long id);

    SubCategoryDto getCategoryByName(String name);
}
