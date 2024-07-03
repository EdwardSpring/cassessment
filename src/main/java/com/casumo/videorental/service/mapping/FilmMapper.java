package com.casumo.videorental.service.mapping;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.casumo.videorental.entity.Film;
import com.casumo.videorental.service.dto.FilmDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = { FilmTypeMapper.class })
public interface FilmMapper extends EntityMapper<Film, FilmDTO> {

}
