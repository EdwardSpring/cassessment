package com.casumo.videorental.service;

import java.util.Optional;

import com.casumo.videorental.service.dto.FilmDTO;

public interface FilmService {
    Optional<FilmDTO> findOne(Long id);

    FilmDTO update(FilmDTO dto);
}

