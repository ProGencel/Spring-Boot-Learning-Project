package com.works.dto;

import com.works.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@Data //Data olunca set methotlarida oluyor value de yok o metotlar
public class CustomerLoginRequestDto{
    @NotNull
    @NotEmpty
    @NotBlank
    String username;
    @NotNull
    @NotEmpty
    @NotBlank
    String password;
}