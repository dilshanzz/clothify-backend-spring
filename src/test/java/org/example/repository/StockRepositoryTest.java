package org.example.repository;

import org.example.entity.Product;
import org.example.entity.Stock;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Stock Repository Testing")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class StockRepositoryTest {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ProductRepository productRepository;

    @Nested
    @Order(1)
    @DisplayName("Save repository")
    class StockRepositorySave {

        @Test
        @Order(1)
        @DisplayName("Save stock Repository")
        public void StockRepository_SaveStock_ReturnStockObject() {
            //Given
            Product product= Product.builder().id(null).build();
            Product saved1 = productRepository.save(product);
            //When
            Stock stock = Stock.builder().id(null).price(2000.00).qty(20).color("Red").size("L").product(Product.builder().id(saved1.getId()).build()).build();
            Stock saved = stockRepository.save(stock);
            //Then
            Assertions.assertEquals(stock.getId(), saved.getId());
            Assertions.assertEquals(stock.getProduct().getId(),saved.getProduct().getId());


        }
    }
    @Nested
    @Order(2)
    @DisplayName("Update repository")
    class StockRepositoryUpdate{

        @Test
        @Order(1)
        @DisplayName("Update stock Repository")
        public void StockRepository_UpdateStock_ReturnStockObject(){
            //Given
            Stock stock = Stock.builder().id(null).price(2000.00).qty(20).color("Red").size("L").product(null).build();

            //When
            Stock saved = stockRepository.save(stock);
            saved.setPrice(3200.00);
            saved.setQty(19);
            saved.setColor("Black");
            saved.setSize("M");
            //Then
            Assertions.assertEquals(saved.getPrice(),3200.00);
            Assertions.assertEquals(saved.getQty(),19);
            Assertions.assertEquals(saved.getColor(),"Black");
            Assertions.assertEquals(saved.getSize(),"M");
        }
    }

    @Nested
    @Order(3)
    @DisplayName("Delete Repository")
    class StockRepositoryDelete{

        @Test
        @Order(1)
        @DisplayName("Delete Stock Repository")
        public void StockRepository_DeleteStock_ReturnVoid()throws Exception {
            //Given
            Stock stock = Stock.builder().id(null).price(2000.00).qty(20).color("Red").size("L").product(null).build();

            //When
            Stock saved = stockRepository.save(stock);
            stockRepository.deleteById(saved.getId());
            Optional<Stock> byId = stockRepository.findById(stock.getId());

            //Then
            assertNull(byId.orElse(null));
        }


        @Nested
        @Order(4)
        @DisplayName("View Repository")
        class StockRepositoryView{

            @Test
            @Order(1)
            @DisplayName("View StockById Repository")
            public  void  StockRepository_GetById_ReturnVoid(){
                //Given
                Stock stock = Stock.builder().id(null).price(2000.00).qty(20).color("Red").size("L").product(null).build();

                //When
                Stock saveStock =stockRepository.save(stock);
                Optional<Stock> byId = stockRepository.findById(saveStock.getId());
                Stock stockGotByOptional=byId.get();
                //Then
                Assertions.assertEquals(stockGotByOptional.getId(),saveStock.getId());
            }

        }
    }
}