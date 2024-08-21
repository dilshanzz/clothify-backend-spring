package org.example.repository;

import org.example.entity.BillingInfo;
import org.example.entity.Customer;
import org.example.entity.Orders;
import org.example.util.enums.OrderStatus;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@DisplayName("Billing Information Repository Testing")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BillingInfoRepositoryTest {
    @Autowired
    BillingInfoRepository billingInfoRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository ordersRepository;


    @Nested
    @Order(1)
    @DisplayName("Save repository")
    class BillInformationRepositorySave {

        @Test
        @Order(1)
        @DisplayName("Save BillingInfo Repository")
        public void BillingInfoRepository_SaveBillingInfo_ReturnStockObject() {
            //Given
            Customer customer= Customer.builder().id(null).build();
            Customer save = customerRepository.save(customer);

            Orders orders= Orders.builder().id(null).status(OrderStatus.valueOf("DELIVERED")).tot((long) 200.00).build();
            Orders save1 = ordersRepository.save(orders);
            //When
            BillingInfo billingInfo = BillingInfo.builder().id(null).address("Mount-Lavinia").phone("0777007987").customer(Customer.builder().id(save.getId()).build()).orders(Orders.builder().status(save1.getStatus()).id(save1.getId()).build()).build();
            BillingInfo saved = billingInfoRepository.save(billingInfo);
            //Then
            Assertions.assertEquals(billingInfo.getId(), saved.getId());
            Assertions.assertEquals(billingInfo.getCustomer().getId(),saved.getCustomer().getId());
            Assertions.assertEquals(billingInfo.getCustomer().getId(),saved.getOrders().getId());


        }
    }
    @Nested
    @Order(2)
    @DisplayName("Update repository")
    class BillInformationRepositoryUpdate{

        @Test
        @Order(1)
        @DisplayName("Update BillingInfo Repository")
        public void BillingInfoRepository_UpdateBillingInfo_ReturnStockObject(){
            //Given
            BillingInfo billingInfo = BillingInfo.builder().id(null).address("Mount-Lavinia").phone("0777007987").customer(null).orders(null).build();


            //When
            BillingInfo saved = billingInfoRepository.save(billingInfo);
            saved.setAddress("Gall-Face");
            saved.setPhone("0772357299");
            //Then
            Assertions.assertEquals(saved.getAddress(),"Gall-Face");
            Assertions.assertEquals(saved.getPhone(),"0772357299");
        }
    }


}