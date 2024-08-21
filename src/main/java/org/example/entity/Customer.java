package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "customer")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Email")
    private String mail;

    @Column(name = "UserName")
    private String name ;

    @Column(name = "Address")
    private String address;

    @Column(name ="Contact")
    private String phone;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Orders> orders;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BillingInfo> billingInfo;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private List<Cart> cart;
}
