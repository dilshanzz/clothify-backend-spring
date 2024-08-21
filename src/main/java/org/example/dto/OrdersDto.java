package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.entity.BillingInfo;
import org.example.entity.Cart;
import org.example.entity.Customer;
import org.example.util.enums.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrdersDto {
    @Valid
    private Long id;
    private String customerId ;
    private Cart cart;
    @NotBlank(message = "address is null")
    private String address;
    @NotBlank(message = "contact is null")
    @Size(min = 10,message = "contact missing digits")
    private String phone;
    @NotBlank(message = "tax  is null")
    private Double tax;
    @NotBlank(message = "charging is null")
    private Double charge;
    @NotBlank(message = "zipcode is null")
    private String zipCode;
    @NotBlank(message = "Total is null")
    private Double tot;
    @NotBlank(message = "city is null")
    @Size(min = 5,message = "contact missing digits")
    private String city;
    private BillingInfo billingInfo;
    private Customer customer;
    private OrderStatus status;
}