package com.furkancitilci.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavMoviesRequestDto {

    private Long movieId;
    private Long userId;

}
