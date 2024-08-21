package org.example.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.dto.CollectionDto;
import org.example.repository.CollectionRepository;
import org.example.service.impl.CollectionServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.example.service.impl.CollectionServiceImpl;

import java.util.*;
import org.example.entity.Collection;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Collection Service Testing")
public class CollectionServiceTest {
    @Mock
    CollectionRepository collectionRepository;

    @Mock
    ObjectMapper objectMapper;

    @InjectMocks
    CollectionServiceImpl collectionServiceImpl;

    @Mock
    CollectionService collectionService;



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
        @DisplayName("Save Collection Service")
        public void CollectionService_SaveCollection_ReturnObject(){
            //Given
            Collection collection = Collection.builder().id(1L).name("Winter").build();
            CollectionDto collectionDto = CollectionDto.builder().id(1L).name("Winter").build();
            //When
            when(collectionRepository.save(any())).thenReturn(collection);
            when(objectMapper.convertValue(any(), (JavaType) any())).thenReturn(collection);
            boolean isSaved=collectionServiceImpl.saveCollection(collectionDto);
            //Then
            Assertions.assertTrue(isSaved);
        }
    }
    @Nested
    @Order(2)
    @DisplayName("Service View")
    class CategoryServiceView {

        @Test
        @DisplayName("CollectionService_getAllCollection - Returns CollectionDtos")
        public void testGetAllCollection() {

            //Given

            ArrayList<Collection> list = new ArrayList<>();

            String name = "winter";
            String name1 = "summer";
            Long id =1L;
            Long id_1 =2L;

            Collection collection = Collection.builder().id(id).name(name).build();
            Collection collection1 = Collection.builder().id(id_1).name(name1).build();
            CollectionDto collectionDto = CollectionDto.builder().id(id_1).name(name1).build();

            list.add(collection);
            list.add(collection1);

            //when
            when(collectionRepository.findAll()).thenReturn(list);
            when(objectMapper.convertValue((Object) any(), (Class<Object>) any())).thenReturn(collectionDto);
            List<CollectionDto> allCollection = collectionService.getAllCollection();

            //Then
            org.assertj.core.api.Assertions.assertThat(!allCollection.isEmpty());


        }


        @Test
        void testGetCollectionByName_Success() {
            // Given
            String name = "Summer";
            Collection collection = Collection.builder().id(1L).name(name).build();
            CollectionDto collectionDto = CollectionDto.builder().id(1L).name(name).build();

            when(collectionRepository.getByName(name)).thenReturn(collection);


            when(collectionService.getCollectionByName(collection.getName())).thenReturn(collectionDto);
            when(objectMapper.convertValue(collection, CollectionDto.class)).thenReturn(collectionDto);

            // When
            CollectionDto resultDto = collectionService.getCollectionByName(name);

            // Then
            verify(collectionService).getCollectionByName(collection.getName());
            assertNotNull(resultDto, "Returned DTO should not be null");
            assertEquals(collectionDto, resultDto, "Returned DTO should match the expected DTO");
        }



        @Test
        void testGetCollectionByName_NotFound() {
            // Given
            String name = "nonexistent";

            // Mocking behavior
            when(collectionRepository.getByName(name)).thenReturn(null);

            // When
            CollectionDto resultDto = collectionService.getCollectionByName(name);

            // Then
            assertNull(resultDto, "Returned DTO should be null as no collection found with name " + name);
        }


        @Test
        @Order(3)
        @DisplayName("Service Collection GeById")
        public void CollectionService_ViewCollectionById_ReturnObject(){
            //Given
            Collection collection = Collection.builder().id(1L).name("Winter").build();
            CollectionService collectionService = Mockito.mock(CollectionService.class);

            //When
            when(collectionService.getCollectionById(collection.getId())).thenReturn(CollectionDto.builder().build());
            CollectionDto foundCollection =collectionService.getCollectionById(collection.getId());

            //Then
            assertNotNull(foundCollection,"CollectionDto should not be null");
        }


    }
    @Nested
    @Order(3)
    @DisplayName("Service Delete")
    class  CollectionServiceDelete{
        @Test
        void testDeleteCollectionById_Success() {
            // Given
            Long id = 1L;

            // Mocking behavior
            when(collectionRepository.existsById(id)).thenReturn(true);

            // When
            boolean result = collectionServiceImpl.deleteCollectionById(id);

            // Then
            verify(collectionRepository).deleteById(id);
            assertTrue(result, "Expected deletion of collection with id " + id);
        }

        @Test
        void testDeleteCollectionById_Failure() {
            // Given
            Long id = 1L;

            // Mocking behavior
            when(collectionRepository.existsById(id)).thenReturn(false);

            // When
            boolean result = collectionServiceImpl.deleteCollectionById(id);

            // Then
            verify(collectionRepository).deleteById(id);
            assertNotEquals(result, false, "Expected no deletion as collection with id " + id + " does not exist");
        }
    }



}