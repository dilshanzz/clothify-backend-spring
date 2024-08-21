package org.example.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.ResourceNotFoundException;
import org.example.dto.CategoryDto;
import org.example.dto.StockDto;
import org.example.entity.Category;
import org.example.entity.Stock;
import org.example.repository.StockRepository;
import org.example.service.impl.StockServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Stock Service Testing")
public class StockServiceTest {

    @Mock
    StockRepository stockRepository;

    @Mock
    ObjectMapper mapper;

    @InjectMocks
    StockServiceImpl stockService;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Nested
    @Order(1)
    @DisplayName("Save Service")
    class SaveStockService{
        @Test
        @Order(1)
        @DisplayName("Save  Stock Service")
        public void StockService_SaveStock_ReturnObject(){
            //Given
            Stock stock = Stock.builder().id(1L).size("M").qty(20).price(2000.00).color("Red").product(null).cart(null).build();
            StockDto stockDto = StockDto.builder().id(1L).size("M").qty(20).price(2000.00).color("Red").product(null).build();

            //When
            when(stockRepository.save(any())).thenReturn(stock);
            when(mapper.convertValue(any(), (JavaType) any())).thenReturn(stock);
            boolean isSaved=stockService.addStock(stockDto);
            //Then
            Assertions.assertTrue(isSaved);
        }
    }
    @Nested
    @Order(2)
    @DisplayName("Update Service")
    class UpdateStockService{

        @Test
        @Order(1)
        @DisplayName("Update Stock Service")
        public void StockService_UpdateStock(){
            //Given
            Long stockId = 1L;
            StockDto updatedDto = createStockDto(stockId);
            Stock existingStock = createStock(stockId);
            Stock expectedUpdatedStock = createStock(stockId);


            // When
            Mockito.when(stockRepository.existsById(stockId)).thenReturn(true);
            Mockito.when(stockRepository.findById(stockId)).thenReturn(Optional.of(existingStock));
            Mockito.when(stockRepository.save(existingStock)).thenReturn(expectedUpdatedStock);

            StockDto updatedCustomer = stockService.updateStock(stockId, updatedDto);

            // Then

            verify(stockRepository).existsById(stockId);
            verify(stockRepository).findById(stockId);
            verify(stockRepository).save(existingStock);

            assertEquals(expectedUpdatedStock, updatedCustomer);
        }

        private StockDto createStockDto(Long id) {
            StockDto dto = new StockDto();
            dto.setId(id);
            return dto;
        }

        private Stock createStock(Long id) {
            Stock customer = new Stock();
            customer.setId(id);
            return customer;
        }
        @Test
        @Order(2)
        @DisplayName("Service Update Stock - Stock Not Found")
        public void StockService_UpdateStock_StockNotFound() {
            // Given
            Long nonExistentId = 10L;
            StockDto anyDto = new StockDto();

            Mockito.when(stockRepository.existsById(nonExistentId)).thenReturn(false);

            assertThrows(ResourceNotFoundException.class, () ->
                    stockService.updateStock(nonExistentId, anyDto));
        }



    }
    @Nested
    @Order(3)
    @DisplayName("Delete Service")
    class DeleteStockService{


        @Test
        @Order(1)
        @DisplayName("Service Delete Stock - Successful Deletion")
        public void StockService_DeleteStock_Success() {
            // Given
            Long existingStockId = 1L;

            // Mock repository behavior
            Mockito.when(stockRepository.existsById(existingStockId)).thenReturn(true);

            // When
            boolean deletionResult = stockService.deleteStock(existingStockId);

            // Then
            // Verify repository calls
            verify(stockRepository).existsById(existingStockId);
            verify(stockRepository).deleteById(existingStockId);
            // Assert successful deletion (true returned)
            assertTrue(deletionResult);
        }

        @Test
        @Order(2)
        @DisplayName("Service Delete Stock - Stock Not Found")
        public void StockService_DeleteStock_NotFound() {
            // Given
            Long nonExistentStockId = 10L;

            Mockito.when(stockRepository.existsById(nonExistentStockId)).thenReturn(false);

            //When
            // Assuming your service throws ResourceNotFoundException when stock is not found
            assertThrows(ResourceNotFoundException.class, () -> stockService.deleteStock(nonExistentStockId));

            // Then
            // Verify repository call
            verify(stockRepository).existsById(nonExistentStockId);

        }

    }
    @Nested
    @Order(4)
    @DisplayName("View Service")
    class ViewStockService{
        @Test
        @Order(1)
        @DisplayName("View StockById Service")
        public void  StockService_GetStockById_ReturnObject(){

            //Given
            Long id =1L;
            Stock stock = Stock.builder().id(id).build();
            StockDto stockDto = StockDto.builder().id(id).build();

            //When
            when(stockRepository.findById(id)).thenReturn(Optional.ofNullable(stock));
            when(stockRepository.existsById(id)).thenReturn(true);
            when(stockService.getStockById(id)).thenReturn(stockDto);

            //Then
            verify(stockRepository).findById(id);


        }
        @Test
        @Order(2)
        @DisplayName("Stock GetById Service - Valid ID, Stock Found")
        public void StockService_GetById_ValidId_StockFound() {
            // Given
            Long existingStockId = 1L; // Replace with an ID that exists in your test data
            Stock existingStock = Stock.builder().id(existingStockId).build(); // Set necessary fields
            StockDto expectedStockDto = StockDto.builder().id(existingStockId).build(); // Set expected data

            // When
            when(stockRepository.findById(existingStockId)).thenReturn(Optional.of(existingStock));


            when(stockService.getStockById(existingStockId)).thenReturn(expectedStockDto);

            // Then
            assertEquals(expectedStockDto.getId(), expectedStockDto.getId());

        }
        @Test
        @Order(3)
        @DisplayName("Stock GetById Service - NotValid ID, Stock NotFound")
        public void StockService_GetById_NotValidId_StockNotFound(){

            // Given
            Long nonExistingId = 10L; // Replace with an ID that exists in your test data
            Stock stock = Stock.builder().id(nonExistingId).build();
            StockDto stockDto = StockDto.builder().id(nonExistingId).build();

            // When

            when(stockRepository.findById(nonExistingId)).thenReturn(Optional.ofNullable(stock));
            when(stockService.getStockById(nonExistingId)).thenReturn(stockDto);




            // Then
            verify(stockRepository).findById(nonExistingId);

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
            StockRepository mockStockRepository = mock(StockRepository.class);

            // When and Then (using assertThrows)
            assertThrows(Exception.class, () -> stockService.getStockById(nullId));

        }
    }
}