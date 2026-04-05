package com.works.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.works.entity.Note}
 */
@Value
public class NoteUpdateRequestDto implements Serializable {
    @Min(1)
    @Max(Long.MAX_VALUE)
    long id;
    @NotNull
    @NotEmpty
    @NotBlank
    String title;
    @NotNull
    @NotEmpty
    @NotBlank
    String detail;
    @Min(1)
    int noteDay;
}