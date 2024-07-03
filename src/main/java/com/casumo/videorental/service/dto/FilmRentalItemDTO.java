package com.casumo.videorental.service.dto;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentalItemDTO {

    private Long id;

    private Integer daysRequested;

    private BigDecimal fee;

    private Instant dueDate;

    private Instant dateReturned;

    private Long extraDays;

    private BigDecimal surcharge;

    private Long filmId;

    private Long filmRentalId;

    @Builder.Default
    private Boolean isReturned = Boolean.FALSE;
}