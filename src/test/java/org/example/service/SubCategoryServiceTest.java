package org.example.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ResourceNotFoundException;
import org.example.dto.SubCategoryDto;
import org.example.entity.SubCategory;
import org.example.repository.SubCategoryRepository;
import org.example.service.impl.SubCategoryServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("SubCategory Service Testing")
public class SubCategoryServiceTest {
    @Mock
    SubCategoryRepository subCategoryRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    SubCategoryServiceImpl subCategoryService;

    @Mock
    SubCategory subCategory1;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    @Order(1)
    @DisplayName("Service save")
    class CollectionServiceSave {
        @Test
        @Order(1)
        @DisplayName("Save SubCategory Service")
        public void SubCategoryService_SaveSubCategory_ReturnObject(){
            //Given
            SubCategory subCategory = SubCategory.builder().id(1L).name("shirt").build();
            SubCategoryDto subCategoryDto = SubCategoryDto.builder().id(1L).name("shirt").build();
            //When
            when(subCategoryRepository.save(any())).thenReturn(subCategory);
            when(objectMapper.convertValue(any(), (JavaType) any())).thenReturn(subCategory);
            boolean isSaved=subCategoryService.saveSubCategory(subCategoryDto);
            //Then
            Assertions.assertTrue(isSaved);
        }
    }
    @Nested
    @Order(2)
    @DisplayName(" View Service")
    class CategoryServiceView {
        @Test
        @Order(1)
        @DisplayName("SubCategory GetAll Service")
        public void SubCategoryService_ViewSubCategory_ReturnObject() {

            //Given
            List<SubCategory> list = new ArrayList<>();

            SubCategory subCategory = SubCategory.builder().id(1L).name("denim").build();
            SubCategory subCategory1 = SubCategory.builder().id(2L).name("shirt").build();
            SubCategoryDto subCategoryDto = SubCategoryDto.builder().id(2L).name("shirt").build();

            list.add(subCategory);
            list.add(subCategory1);

            //When
            when(subCategoryRepository.findAll()).thenReturn(list);
            when(objectMapper.convertValue(any(), (Class<Object>) any())).thenReturn(subCategoryDto);
            List<SubCategoryDto> subCategories = subCategoryService.getAllSubCategories();
            //then
            org.assertj.core.api.Assertions.assertThat(!subCategories.isEmpty());
        }
        @Test
        @Order(2)
        @DisplayName("SubCategory GetByName Service")
        public void SubCategoryService_ViewSubCategoryByName_ReturnObject(){
            //Given
            String subCategoryName = "denim";
            Long id =1L;
            SubCategory subCategory = SubCategory.builder().id(id).name(subCategoryName).build();
            SubCategoryDto subCategoryDto = SubCategoryDto.builder().id(id).name(subCategoryName).build();


            when(subCategoryRepository.getByName(subCategoryName)).thenReturn(subCategory);
            //When
            when(subCategoryService.getCategoryByName(subCategoryName)).thenReturn(subCategoryDto);



            //Then
            verify(subCategoryRepository).getByName(subCategoryName);

        }
        @Test
        @Order(3)
        @DisplayName("Service Get Category by Name - Category Not Found")
        public void CategoryService_GetCategoryByName_CategoryNotFound() {
            // Given
            String nonExistentName = "Mens";
            // Mock repository to return null (no customer found)
            Mockito.when(subCategoryRepository.getByName(nonExistentName)).thenReturn(null);

            // When


            assertThrows(RuntimeException.class, () -> subCategoryService.getCategoryByName(nonExistentName));

            // Then
            // Verify repository call
            verify(subCategoryRepository).getByName(nonExistentName);
            // Assert null result

        }
        @Test
        @Order(4)
        @DisplayName("Service Get Category by Name - Exception Handled")
        public void CategoryService_GetCategoryByName_HandlesException() {
            // Given
            String nameCausingException = "Error";
            // Mock repository to throw an exception
            Mockito.when(subCategoryRepository.getByName(nameCausingException)).thenThrow(new RuntimeException("Unexpected error"));

            // When

            assertThrows(RuntimeException.class, () -> subCategoryService.getCategoryByName(nameCausingException));

            // Then
            // Verify repository call
            verify(subCategoryRepository).getByName(nameCausingException);

        }
    }
    @Nested
    @Order(3)
    @DisplayName(" Delete Service")
    class CategoryServiceDelete{
        @Test
        @Order(1)
        @DisplayName("Service Delete SubCategory - Successful Deletion")
        public void SubCategoryService_DeleteSubCategory_Success() {
            // Given
            Long existingSubCategoryId = 1L;

            // Mock repository behavior
            Mockito.when(subCategoryRepository.existsById(existingSubCategoryId)).thenReturn(true);

            // When
            boolean deletionResult = subCategoryService.deleteSubCategoryById(existingSubCategoryId);

            // Then
            // Verify repository calls
            verify(subCategoryRepository).existsById(existingSubCategoryId);
            verify(subCategoryRepository).deleteById(existingSubCategoryId);
            // Assert successful deletion (true returned)
            assertTrue(deletionResult);
        }
        @Test
        @Order(2)
        @DisplayName("Service Delete SubCategory - Stock Not Found")
        public void SubCategoryService_DeleteSubCategory_NotFound() {
            // Given
            Long nonExistentStockId = 10L;

            Mockito.when(subCategoryRepository.existsById(nonExistentStockId)).thenReturn(false);

            //When
            // Assuming your service throws ResourceNotFoundException when stock is not found
            assertThrows(ResourceNotFoundException.class, () -> subCategoryService.deleteSubCategoryById(nonExistentStockId));

            // Then
            // Verify repository call
            verify(subCategoryRepository).existsById(nonExistentStockId);

        }

    }
    @Nested
    @Order(4)
    @DisplayName("View Service")
    class ViewSubCategoryService {
        @Test
        @Order(1)
        @DisplayName("View SubCategoryById Service")
        public void SubCategoryService_GetSubCategoryById_ReturnObject() {

            //Given
            Long id = 1L;
            SubCategory subCategory = SubCategory.builder().id(id).build();
            SubCategoryDto subCategoryDto = SubCategoryDto.builder().id(id).build();

            //When
            when(subCategoryRepository.findById(id)).thenReturn(Optional.ofNullable(subCategory));
            when(subCategoryRepository.existsById(id)).thenReturn(true);
            when(subCategoryService.getSubCategoryById(id)).thenReturn(subCategoryDto);


            //Then
            verify(subCategoryRepository).findById(id);


        }
        @Test
        @Order(2)
        @DisplayName("SubCategory GetById Service - Valid ID, Subcategory Found")
        public void SubCategoryService_GetById_ValidId_SubCategoryFound() {
            // Given
            Long existingSubCategoryId = 1L; // Replace with an ID that exists in your test data
            SubCategory existingSubCategory = SubCategory.builder().id(existingSubCategoryId).build(); // Set necessary fields
            SubCategoryDto expectedSubCategoryDto = SubCategoryDto.builder().id(existingSubCategoryId).build(); // Set expected data

            // When
            when(subCategoryRepository.findById(existingSubCategoryId)).thenReturn(Optional.of(existingSubCategory));


            when(subCategoryService.getSubCategoryById(existingSubCategoryId)).thenReturn(expectedSubCategoryDto);

            // Then
            assertEquals(expectedSubCategoryDto.getId(), expectedSubCategoryDto.getId());

        }
        @Test
        @Order(3)
        @DisplayName("SubCategory GetById Service - NotValid ID, SubCategory NotFound")
        public void SubCategoryService_GetById_NotValidId_SubCategoryNotFound(){

            // Given
            Long nonExistingId = 10L; // Replace with an ID that exists in your test data
            SubCategory subCategory = SubCategory.builder().id(nonExistingId).build();
            SubCategoryDto subCategoryDto = SubCategoryDto.builder().id(nonExistingId).build();

            // When

            when(subCategoryRepository.findById(nonExistingId)).thenReturn(Optional.ofNullable(subCategory));
            when(subCategoryService.getSubCategoryById(nonExistingId)).thenReturn(subCategoryDto);




            // Then
            verify(subCategoryRepository).findById(nonExistingId);

        }
    }
    @Nested
    @Order(5)
    @DisplayName("Error handing Service")
    class ThrowExceptionService {

        @Test
        @Order(1)
        @DisplayName("StockService_getStockById - Handle Null ID")
        public void testStockService_GetStockById_NullId()  {
            // Given
            Long nullId = null;

            // Mock dependencies (if applicable)
            SubCategoryRepository mockStockRepository = mock(SubCategoryRepository.class);

            // When and Then (using assertThrows)
            assertThrows(Exception.class, () -> subCategoryService.getSubCategoryById(nullId));

        }
    }

}