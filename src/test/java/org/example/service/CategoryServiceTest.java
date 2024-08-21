package org.example.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.example.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Category Service Testing")
public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @Order(1)
    @DisplayName(" save Service")
    class CategoryServiceSave {
        @Test
        @Order(1)
        @DisplayName("Save Category Service")
        public void CategoryService_SaveCategory_ReturnObject(){
            //Given
            Category category = Category.builder().id(1L).name("Mens").build();
            CategoryDto categoryDto = CategoryDto.builder().id(1L).name("Mens").build();
            //When
            when(categoryRepository.save(any())).thenReturn(category);
            when(objectMapper.convertValue(any(), (JavaType) any())).thenReturn(category);
            boolean isSaved=categoryService.saveCategory(categoryDto);
            //Then
            Assertions.assertTrue(isSaved);
        }
    }
    @Nested
    @Order(2)
    @DisplayName(" View Service")
    class CategoryServiceView{
        @Test
        @Order(1)
        @DisplayName("Category GetAll Service")
        public void CategoryService_ViewCategory_ReturnObject(){

            //Given
            List<Category> list= new ArrayList<>();

            Category category =  Category.builder().id(1L).name("Mens").build();
            Category category1 =  Category.builder().id(2L).name("womens").build();
            CategoryDto categoryDto=CategoryDto.builder().id(2L).name("womens").build();
            list.add(category1);
            list.add(category);

            //When
            when(categoryRepository.findAll()).thenReturn(list);
            when(objectMapper.convertValue(any(), (Class<Object>) any())).thenReturn(categoryDto);
            List<CategoryDto>categoriesGot=categoryService.getAllCategories();
            //then
            org.assertj.core.api.Assertions.assertThat(!categoriesGot.isEmpty());
        }
        @Test
        @Order(2)
        @DisplayName("Category GetByName Service")
        public void CategoryService_ViewCategoryByName_ReturnObject(){
            //Given
            String categoryName = "Mens";
            Long id =1L;
            Category category = Category.builder().id(id).name(categoryName).build();
            CategoryDto categoryDto = CategoryDto.builder().id(id).name(categoryName).build();


            when(categoryRepository.getByName(categoryName)).thenReturn(category);
            //When
            when(categoryService.getCategoryByName(categoryName)).thenReturn(categoryDto);



            //Then
            verify(categoryRepository).getByName(categoryName);

        }
        @Test
        @Order(3)
        @DisplayName("Service Get Category by Name - Category Not Found")
        public void CategoryService_GetCategoryByName_CategoryNotFound() {
            // Given
            String nonExistentName = "Mens";
            // Mock repository to return null (no customer found)
            Mockito.when(categoryRepository.getByName(nonExistentName)).thenReturn(null);

            // When
            CategoryDto categoryDto = categoryService.getCategoryByName(nonExistentName);

            // Then
            // Verify repository call
            verify(categoryRepository).getByName(nonExistentName);
            // Assert null result
            assertNull(categoryDto);
        }
        @Test
        @Order(4)
        @DisplayName("Service Get Category by Name - Exception Handled")
        public void CategoryService_GetCategoryByName_HandlesException() {
            // Given
            String nameCausingException = "Error";
            // Mock repository to throw an exception
            Mockito.when(categoryRepository.getByName(nameCausingException)).thenThrow(new RuntimeException("Unexpected error"));

            // When
            CategoryDto categoryDto = categoryService.getCategoryByName(nameCausingException);

            // Then
            // Verify repository call
            verify(categoryRepository).getByName(nameCausingException);

        }



    }
    @Nested
    @Order(3)
    @DisplayName(" Delete Service")
    class CategoryServiceDelete{
        @Test
        @Order(1)
        @DisplayName("Category DeleteByName Service")
        public void CategoryService_DeleteCategoryByName_ReturnVoid(){
            // Given
            String categoryName = "Mens";
            Long id =1L;
            Category category = Category.builder().id(id).name(categoryName).build();
            CategoryDto categoryDto = CategoryDto.builder().id(1L).name(categoryName).build();


            // When

            when(categoryService.getCategoryByName(categoryName)).thenReturn(categoryDto);
            doNothing().when(categoryRepository).deleteById(category.getId());

            boolean isDelete = categoryService.deleteCategoryByName(categoryName);

            // Then
            verify(categoryRepository).deleteById(category.getId());
            assertTrue(isDelete);
        }

    }
    @Nested
    @Order(4)
    @DisplayName("Error handing Service")
    class ThrowExceptionService{

        @Test
        @Order(1)
        @DisplayName("Category GetByName Service - Error Handling")
        public void CategoryService_GetCategoryByName_ReturnEmptyDto(){
            //Given
            String categoryName = null;
            Long id = null;
            Category category = Category.builder().id(id).name(categoryName).build();
            CategoryDto categoryDto = CategoryDto.builder().id(id).name(categoryName).build();

            //When
            when(categoryRepository.getByName(categoryName)).thenReturn(category);
            when(categoryService.getCategoryByName(categoryName)).thenReturn(categoryDto);

            //Then
            verify(categoryRepository).getByName(categoryName);
            assertNotNull(categoryDto);
        }



    }


}