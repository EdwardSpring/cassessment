package com.casumo.videorental.service.mapping;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.casumo.videorental.entity.FilmRental;
import com.casumo.videorental.service.dto.FilmRentalDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = { FilmRentalItemMapper.class })
public interface FilmRentalMapper extends EntityMapper<FilmRental, FilmRentalDTO> {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    FilmRentalDTO toDTO(FilmRental e);

    @Mapping(source = "customerId", target = "customer.id")
    FilmRental toEntity(FilmRentalDTO dto);

}
