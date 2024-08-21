package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.SubCategoryDto;
import org.example.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/subCategory")
public class SubCategoryController {
    @Autowired
    SubCategoryService subCategoryService;
    @PostMapping("/add")
    public boolean addSubcategory(@Valid @RequestBody SubCategoryDto subCategoryDto){
        return subCategoryService.saveSubCategory(subCategoryDto);
    }

    @GetMapping("/getAll")
    public List<SubCategoryDto> getAllSubCategories(){
        return subCategoryService.getAllSubCategories();
    }

    @GetMapping("/get/{id}")
    public SubCategoryDto getSubcategoryById(@PathVariable Long id){
        return subCategoryService.getSubCategoryById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSubCategoryById(@PathVariable Long id){
       boolean isDeleted= subCategoryService.deleteSubCategoryById(id);
       return isDeleted ?"Sub category is deleted":"Something went wrong";
    }
    @GetMapping("/get/name/{name}")
    public SubCategoryDto getSubcategoryByName(@PathVariable String name){
       return subCategoryService.getCategoryByName(name);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> error(MethodArgumentNotValidException exception){
        Map <String, String> map=new HashMap<>();
        List<ObjectError> list=exception.getBindingResult().getAllErrors();
        for(ObjectError item:list) {
            FieldError fieldError=(FieldError) item;
            String fieldName= fieldError.getField();
            String message = item.getDefaultMessage();
            map.put(fieldName,message);
        }
        return map;
    }
}
