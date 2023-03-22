package com.furkancitilci.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieAdminPageResponseDto {

    private Long id;
    private String language;
    private String name;

    private LocalDate premiered;
}
