package com.casumo.videorental.service;

import java.util.Set;

import com.casumo.videorental.service.dto.FilmRentalDTO;

public interface FilmRentalService {

    FilmRentalDTO rentFilms(FilmRentalDTO dto);

    FilmRentalDTO returnFilms(Long filmRentalId, Set<Long> dtos);
}
