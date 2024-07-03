package com.casumo.videorental.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.casumo.videorental.entity.Film;
import com.casumo.videorental.repository.FilmRepository;
import com.casumo.videorental.service.FilmService;
import com.casumo.videorental.service.dto.FilmDTO;
import com.casumo.videorental.service.mapping.FilmMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmMapper filmMapper;
    private final FilmRepository filmRepository;

    @Override
    public Optional<FilmDTO> findOne(Long id) {
        return filmRepository.findById(id).map(filmMapper::toDTO);
    }

    @Override
    public FilmDTO update(FilmDTO dto) {
        Film film = filmMapper.toEntity(dto);
        film = filmRepository.save(film);
        return filmMapper.toDTO(film);
    }

}
