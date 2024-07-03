package com.casumo.videorental.service.mapping;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.casumo.videorental.entity.FilmType;
import com.casumo.videorental.service.dto.FilmTypeDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = { PricingMapper.class })
public interface FilmTypeMapper extends EntityMapper<FilmType, FilmTypeDTO> {

    @Mapping(source = "initialPricing.id", target = "initialPricingId")
    @Mapping(source = "surchargePricing.id", target = "surchargePricingId")
    @Mapping(source = "initialPricing.amount", target = "initialPricingAmount")
    @Mapping(source = "surchargePricing.amount", target = "surchargePricingAmount")
    FilmTypeDTO toDTO(FilmType e);

}
