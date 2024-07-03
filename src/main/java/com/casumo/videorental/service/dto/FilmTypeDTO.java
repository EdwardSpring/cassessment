package com.casumo.videorental.service.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmTypeDTO {

    private Long id;

    private String name;

    private Integer gracePeriod;

    private Long initialPricingId;

    private BigDecimal initialPricingAmount;

    private Long surchargePricingId;

    private BigDecimal surchargePricingAmount;
}

