package com.casumo.videorental.service.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmRentalDTO {
    private Long id;

    private Long customerId;

    private String customerName;

    private Instant dateRented;

    private BigDecimal totalFee;

    private BigDecimal totalSurcharge;

    private Set<FilmRentalItemDTO> items;
}
