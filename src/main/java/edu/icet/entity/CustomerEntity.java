package edu.icet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
//@SequenceGenerator(name = "customer_sequence", sequenceName = "customer_sequence", allocationSize = 1)

public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

    private String customerName;
    private String customerContact;
    private String email;
    private String address;
}
