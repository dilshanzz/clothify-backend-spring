package edu.icet.clothify.repository;

import org.example.entity.SubCategory;
import org.example.repository.SubCategoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName(" SubCategory Repository Testing")
public class SubCategoryRepositoryTest {
    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Nested
    @Order(1)
    @DisplayName("Save repository")
    class SubCategoryRepositorySave{

        @Test
        @Order(1)
        @DisplayName("Save SubCategory Repository")
        public void SubCategoryRepository_SaveSubCategory_ReturnSubCategoryObject(){
            //Given
            SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
            //When
            SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
            //Then
            Assertions.assertEquals(subCategory,savedSubCategory);
        }

    }

    @Nested
    @Order(2)
    @DisplayName("Update repository")
    class SubCategoryRepositoryUpdate {
        @Test
        @Order(1)
        @DisplayName("Update SubCategory Repository")
        public void SubCategoryRepository_UpdateSubCategory_ReturnSubCategoryObject(){
            //Given
            SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
            //When
            SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
            savedSubCategory.setName("Mens");
            //Then
            Assertions.assertEquals(savedSubCategory.getName(),"Mens");
        }
    }

    @Nested
    @Order(3)
    @DisplayName("Delete repository")
    class SubCategoryRepositoryDelete {
        @Test
        @Order(1)
        @DisplayName("Delete SubCategory Repository")
        public void SubCategoryRepository_DeleteSubCategory_ReturnVoid() throws Exception {
            //Given
            SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
            //When
            SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
            subCategoryRepository.deleteById(savedSubCategory.getId());
            Optional<SubCategory> subCategory1 = subCategoryRepository.findById(savedSubCategory.getId());

            //Then
            assertNull(subCategory1.orElse(null));

        }
    }


    @Nested
    @Order(4)
    @DisplayName(" View Repository")
    class SubCategoryRepositoryView{

        @Test
        @Order(1)
        @DisplayName("Get SubCategory By Name Repository")
        public void SubCategoryRepository_GetByName_ReturnObject(){
            //Given
            SubCategory subCategory = SubCategory.builder().id(null).name("Womens").build();
            //When
            SubCategory savedSubCategory=subCategoryRepository.save(subCategory);
            SubCategory getByName=subCategoryRepository.getByName(savedSubCategory.getName());
            //Then
            Assertions.assertEquals(getByName.getName(),savedSubCategory.getName());
        }
    }

}