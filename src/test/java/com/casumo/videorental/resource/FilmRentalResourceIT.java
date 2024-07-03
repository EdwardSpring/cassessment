package com.casumo.videorental.resource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.casumo.videorental.configuration.TestPropertyConfiguration;
import com.casumo.videorental.entity.Customer;
import com.casumo.videorental.entity.Film;
import com.casumo.videorental.entity.FilmRental;
import com.casumo.videorental.entity.FilmRentalItem;
import com.casumo.videorental.entity.FilmType;
import com.casumo.videorental.entity.Pricing;
import com.casumo.videorental.repository.FilmRentalRepository;
import com.casumo.videorental.service.dto.FilmRentalDTO;
import com.casumo.videorental.service.mapping.FilmRentalMapper;
import com.casumo.videorental.util.TestUtil;

import jakarta.persistence.EntityManager;

@SpringBootTest
@AutoConfigureMockMvc
public class FilmRentalResourceIT extends TestPropertyConfiguration {

    private static final int DEFAULT_DAYS_REQUESTED = 3;
    private static final int DEFAULT_COPIES = 3;
    private static final String DEFAULT_FILM_NAME = "Matrix";
    private static final int DEFAULT_COPIES_ON_SHELF = 3;
    private static final Long DEFAULT_FILM_ID = 1L;
    private static final int DEFAULT_GRACE_PERIOD = 3;
    private static final BigDecimal DEFAULT_TOTAL_FEE = BigDecimal.ZERO;
    private static final BigDecimal DEFAULT_FEE = BigDecimal.ZERO;
    private static final Instant DEFAULT_DATE_RENTED = Instant.now().minus(1, ChronoUnit.DAYS);
    private static final Instant DEFAULT_DUE_DATE = Instant.now().minus(1, ChronoUnit.DAYS);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MockMvc restFilmRentalMockMvc;

    @Autowired
    private FilmRentalMapper filmRentalMapper;

    @Autowired
    private FilmRentalRepository filmRentalRepository;

    private FilmRental filmRental;

    public static FilmRental createFilmRental(EntityManager em) {
        Set<FilmRentalItem> items = new HashSet<>();
        items.add(createFilmRentalItem(em));

        FilmRental filmRental = FilmRental
                .builder()
                .customer(createCustomer(em))
                .dateRented(DEFAULT_DATE_RENTED)
                .totalFee(DEFAULT_TOTAL_FEE)
                .items(items)
                .build();

        return filmRental;
    }

    public static Customer createCustomer(EntityManager em) {
        Customer customer = Customer.builder().id(1L).build();
        return customer;
    }

    public static Pricing createPricing(EntityManager em) {
        return Pricing
                .builder()
                .amount(DEFAULT_FEE)
                .build();
    }

    public static FilmType createFilmType(EntityManager em) {
        return FilmType
                .builder()
                .gracePeriod(DEFAULT_GRACE_PERIOD)
                .initialPricing(createPricing(em))
                .surchargePricing(createPricing(em))
                .build();
    }

    public static Film createFilm(EntityManager em) {
        Film film = Film
                .builder()
                .id(DEFAULT_FILM_ID)
                .name(DEFAULT_FILM_NAME)
                .copies(DEFAULT_COPIES)
                .copiesOnShelf(DEFAULT_COPIES_ON_SHELF)
                .type(createFilmType(em))
                .build();
        return film;
    }

    public static FilmRentalItem createFilmRentalItem(EntityManager em) {
        FilmRentalItem filmRentalItem = FilmRentalItem
                .builder()
                .fee(DEFAULT_FEE)
                .daysRequested(DEFAULT_DAYS_REQUESTED)
                .dueDate(DEFAULT_DATE_RENTED)
                .dueDate(DEFAULT_DUE_DATE)
                .isReturned(Boolean.FALSE)
                .build();

        filmRentalItem.setFilm(createFilm(em));
        return filmRentalItem;
    }

    @BeforeEach
    public void init() {
        filmRental = createFilmRental(entityManager);
    }

    @Test
    @Transactional
    public void rentFilmsTest() throws Exception {
        FilmRentalDTO dto = filmRentalMapper.toDTO(filmRental);

        restFilmRentalMockMvc.perform(post("/api/film-rentals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(dto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.items").isNotEmpty());
    }

}
