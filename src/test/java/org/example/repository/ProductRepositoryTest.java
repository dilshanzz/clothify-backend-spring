package org.example.repository;

import org.example.entity.Category;
import org.example.entity.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest{

    @Autowired
    ProductRepository productRepository;

    @Nested
    @Order(1)
    @DisplayName("Save Repository")
    class ProductRepositorySave{
        @Test
        @Order(1)
        @DisplayName("Save Product Repository")
        public void ProductRepository_SaveProduct_ReturnProduct(){
            //Given
            Product product = Product.builder().id(null).name("Mens").price(124.00).desc("No description").category(Category.builder().id(null).name("Womens").build()).soldCount(12).build();

            //When
            Product produtSaved=productRepository.save(product);
            //Then
            Assertions.assertEquals(product.getName(),produtSaved.getName());
        }
    }


    @Test
    public void ProductRepository_GetAllProducts_ReturnList(){
        //Given

        //When

        //Then

    }
}