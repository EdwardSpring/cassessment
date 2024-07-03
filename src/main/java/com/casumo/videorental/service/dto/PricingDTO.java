package com.casumo.videorental.service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PricingDTO {
    private Long id;

    private String name;

    private BigDecimal amount;
}

