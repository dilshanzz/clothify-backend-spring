package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class CustomerDto {
    @Valid
    private Long id;
    @NotBlank(message = "E-mail name is null")
    @Pattern(regexp = "^[A-Za-z0-9]+.*\\.[A-Za-z]+$")
    private String mail;
    @NotBlank(message = " name is null")
    @Size(min = 2,message = "Name is too short try again")
    private String name;
    @NotBlank(message = "address is null")
    private String address;
    @NotBlank(message = "contact is null")
    @Size(min = 10,message = "contact missing digits")
    private String phone;
}
