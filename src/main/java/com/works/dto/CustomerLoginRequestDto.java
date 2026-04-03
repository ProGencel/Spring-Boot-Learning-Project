package com.works.dto;

import com.works.entity.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@Value
public class CustomerLoginRequestDto implements Serializable {//serialize sayesinde
    @NotNull
    @NotEmpty
    @NotBlank
    String username;
    @NotNull
    @NotEmpty
    @NotBlank
    String password;
}