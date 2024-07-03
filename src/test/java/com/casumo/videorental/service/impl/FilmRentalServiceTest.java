package com.casumo.videorental.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;

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
import com.casumo.videorental.entity.FilmRental;
import com.casumo.videorental.entity.FilmRentalItem;
import com.casumo.videorental.exception.FilmOutOfStockException;
import com.casumo.videorental.exception.RepeatedFilmReturnException;
import com.casumo.videorental.repository.FilmRentalRepository;
import com.casumo.videorental.service.FilmService;
import com.casumo.videorental.service.dto.FilmDTO;
import com.casumo.videorental.service.dto.FilmRentalDTO;
import com.casumo.videorental.service.dto.FilmRentalItemDTO;
import com.casumo.videorental.service.dto.FilmTypeDTO;
import com.casumo.videorental.service.mapping.FilmRentalItemMapper;
import com.casumo.videorental.service.mapping.FilmRentalMapper;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FilmRentalServiceTest {

    private final Long DEFAULT_CUSTOMER_ID = 1L;
    private final String DEFAULT_FILM_NAME = "Matrix";
    private final int DEFAULT_DAYS_REQUESTED = 3;
    private static final int DEFAULT_COPIES = 3;
    private static final int DEFAULT_COPIES_ON_SHELF = 3;
    private static final Long DEFAULT_FILM_ID = 1L;
    private static final int DEFAULT_GRACE_PERIOD = 3;
    private static final BigDecimal DEFAULT_TOTAL_FEE = BigDecimal.ZERO;
    private static final BigDecimal DEFAULT_FEE = BigDecimal.ZERO;
    private static final BigDecimal DEFAULT_INITIAL_CHARGE = BigDecimal.TEN;
    private static final BigDecimal DEFAULT_SURCHARGE = BigDecimal.TEN;
    private static final Boolean DEFAULT_IS_RETURNED = Boolean.FALSE;
    private static final Instant DEFAULT_DATE_RENTED = Instant.now().minus(1, ChronoUnit.DAYS);
    private static final Instant DEFAULT_DUE_DATE = Instant.now().minus(1, ChronoUnit.DAYS);

    @InjectMocks
    private FilmRentalServiceImpl filmRentalService;

    private FilmRentalDTO filmRentalDTO;

    @Mock
    private FilmService filmService;

    @Mock
    private FilmRentalRepository filmRentalRepository;

    @Spy
    private FilmRentalItemMapper filmRentalItemMapper = Mappers.getMapper(FilmRentalItemMapper.class);

    @Spy
    private FilmRentalMapper filmRentalMapper = Mappers.getMapper(FilmRentalMapper.class);

    @BeforeEach
    public void init() {
        filmRentalDTO = generateFilmRentalDTO();
        ReflectionTestUtils.setField(filmRentalMapper, "filmRentalItemMapper", filmRentalItemMapper);
    }

    @Test
    public void testRentFilms_NoCopiesLeft() throws Exception {
        FilmDTO filmDTO = generateFilm();
        filmDTO.setCopiesOnShelf(0);

        when(filmService.findOne(any())).thenReturn(Optional.of(filmDTO));

        assertThrows(FilmOutOfStockException.class, () -> {
            filmRentalService.rentFilms(filmRentalDTO);
        });

        verify(filmRentalRepository, times(0)).save(any());
    }

    @Test
    public void testRentFilms_PositiveExcessDays() throws Exception {
        FilmDTO filmDTO = generateFilm();
        FilmRental filmRental = filmRentalMapper.toEntity(filmRentalDTO);

        when(filmService.findOne(any())).thenReturn(Optional.of(filmDTO));
        when(filmRentalRepository.save(any())).thenReturn(filmRental);

        filmRentalService.rentFilms(filmRentalDTO);

        verify(filmRentalRepository, times(1)).save(any());
    }

    @Test
    public void testReturnFilms_SingleItem_ReturnsUpdatedFilmRentalDTO() {

        Long filmRentalId = 1L;
        Set<Long> items = Set.of(1L);
        FilmRentalItemDTO filmRentalItemDTO = FilmRentalItemDTO.builder().id(1L).daysRequested(DEFAULT_DAYS_REQUESTED)
                .filmId(DEFAULT_FILM_ID).dueDate(Instant.now()).build();
        FilmRentalItem filmRentalItem = filmRentalItemMapper.toEntity(filmRentalItemDTO);

        FilmRental filmRental = filmRentalMapper.toEntity(filmRentalDTO);
        filmRental.setItems(Set.of(filmRentalItem));
        when(filmRentalRepository.findById(any())).thenReturn(Optional.of(filmRental));
        filmRentalService.returnFilms(filmRentalId, items);

        verify(filmRentalRepository, times(1)).save(any());
    }

    @Test
    public void testReturnFilms_ItemAlreadyReturned_throwsError() {

        Long filmRentalId = 1L;
        Set<Long> items = Set.of(1L);

        FilmRentalItemDTO filmRentalItemDTO = generateItem();
        filmRentalItemDTO.setIsReturned(Boolean.TRUE);
        FilmRentalItem filmRentalItem = filmRentalItemMapper.toEntity(filmRentalItemDTO);
        filmRentalItem.setFilm(Film.builder().name(DEFAULT_FILM_NAME).build());

        FilmRental filmRental = filmRentalMapper.toEntity(filmRentalDTO);
        filmRental.setItems(Set.of(filmRentalItem));

        when(filmRentalRepository.findById(any())).thenReturn(Optional.of(filmRental));

        assertThrows(RepeatedFilmReturnException.class, () -> {
            filmRentalService.returnFilms(filmRentalId, items);
        });

        verify(filmRentalRepository, times(0)).save(any());
    }

    public FilmDTO generateFilm() {
        FilmTypeDTO filmType = FilmTypeDTO
                .builder()
                .name(DEFAULT_FILM_NAME)
                .initialPricingAmount(DEFAULT_INITIAL_CHARGE)
                .surchargePricingAmount(DEFAULT_SURCHARGE)
                .gracePeriod(DEFAULT_GRACE_PERIOD).build();

        return FilmDTO
                .builder()
                .name(DEFAULT_FILM_NAME)
                .copies(DEFAULT_COPIES)
                .copiesOnShelf(DEFAULT_COPIES_ON_SHELF)
                .type(filmType)
                .build();
    }

    public FilmRentalItemDTO generateItem() {
        return FilmRentalItemDTO
                .builder()
                .id(1L)
                .daysRequested(DEFAULT_DAYS_REQUESTED)
                .filmId(DEFAULT_FILM_ID)
                .isReturned(DEFAULT_IS_RETURNED)
                .dueDate(DEFAULT_DUE_DATE)
                .fee(DEFAULT_FEE)
                .build();

    }

    public FilmRentalDTO generateFilmRentalDTO() {
        return FilmRentalDTO
                .builder()
                .customerId(DEFAULT_CUSTOMER_ID)
                .totalFee(DEFAULT_TOTAL_FEE)
                .totalSurcharge(DEFAULT_SURCHARGE)
                .dateRented(DEFAULT_DATE_RENTED)
                .items(Set.of(generateItem()))
                .build();
    }

}
