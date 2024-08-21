package org.example.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CollectionDto {
    @Valid
    private Long id;
    @NotBlank(message = "category name is null")
    @Size(min = 2,message = "Name is too short try again")
    private String name;
}
