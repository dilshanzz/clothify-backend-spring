package org.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubCategoryDto {
    @Valid
    private Long id;
    @NotBlank(message = "category name is null")
    @Size(min = 2,message = "Name is too short try again")
    private String name;
}
