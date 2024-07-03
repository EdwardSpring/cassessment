package com.casumo.videorental.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.casumo.videorental.entity.Film;
import com.casumo.videorental.repository.FilmRepository;
import com.casumo.videorental.service.dto.FilmDTO;
import com.casumo.videorental.service.mapping.FilmMapper;
import com.casumo.videorental.service.mapping.FilmTypeMapper;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FilmServiceTest {

    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    @Spy
    private FilmMapper filmMapper = Mappers.getMapper(FilmMapper.class);

    @Spy
    private FilmTypeMapper filmTypeMapper = Mappers.getMapper(FilmTypeMapper.class);

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(filmMapper, "filmTypeMapper", filmTypeMapper);
    }

    @Test
    public void testFindOne() throws Exception {
        Film film = Film.builder().copies(1).build();
        Optional<Film> optionalFilm = Optional.of(film);
        Optional<FilmDTO> expectedResponse = optionalFilm.map(filmMapper::toDTO);
        when(filmRepository.findById(any())).thenReturn(optionalFilm);
        Optional<FilmDTO> result = filmService.findOne(any());
        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResponse);
        verify(filmRepository, times(1)).findById(any());
    }

}
