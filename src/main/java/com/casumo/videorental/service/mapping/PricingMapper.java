package com.casumo.videorental.service.mapping;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.casumo.videorental.entity.Pricing;
import com.casumo.videorental.service.dto.PricingDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {})
public interface PricingMapper extends EntityMapper<Pricing, PricingDTO> {

}
