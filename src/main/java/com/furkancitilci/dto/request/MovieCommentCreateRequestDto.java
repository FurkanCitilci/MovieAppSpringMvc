package com.furkancitilci.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieCommentCreateRequestDto {

    private Long userId;
    private Long movieId;
    private String content;
}
