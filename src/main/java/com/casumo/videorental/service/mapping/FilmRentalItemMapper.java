package com.casumo.videorental.service.mapping;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.casumo.videorental.entity.FilmRentalItem;
import com.casumo.videorental.service.dto.FilmRentalItemDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {})
public interface FilmRentalItemMapper extends EntityMapper<FilmRentalItem, FilmRentalItemDTO> {

    @Mapping(source = "filmRental.id", target = "filmRentalId")
    @Mapping(source = "film.id", target = "filmId")
    FilmRentalItemDTO toDTO(FilmRentalItem e);

    @Mapping(source = "filmRentalId", target = "filmRental.id")
    @Mapping(source = "filmId", target = "film.id")
    FilmRentalItem toEntity(FilmRentalItemDTO dto);

}
