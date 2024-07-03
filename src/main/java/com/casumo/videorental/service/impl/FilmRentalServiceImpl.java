package com.casumo.videorental.service.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casumo.videorental.entity.FilmRental;
import com.casumo.videorental.entity.FilmRentalItem;
import com.casumo.videorental.exception.FilmNotFoundException;
import com.casumo.videorental.exception.FilmOutOfStockException;
import com.casumo.videorental.exception.RepeatedFilmReturnException;
import com.casumo.videorental.repository.FilmRentalRepository;
import com.casumo.videorental.service.FilmRentalService;
import com.casumo.videorental.service.FilmService;
import com.casumo.videorental.service.dto.FilmDTO;
import com.casumo.videorental.service.dto.FilmRentalDTO;
import com.casumo.videorental.service.dto.FilmRentalItemDTO;
import com.casumo.videorental.service.dto.FilmTypeDTO;
import com.casumo.videorental.service.mapping.FilmRentalMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FilmRentalServiceImpl implements FilmRentalService {

    private final FilmRentalMapper filmRentalMapper;
    private final FilmService filmService;
    private final FilmRentalRepository filmRentalRepository;

    @Override
    public FilmRentalDTO rentFilms(FilmRentalDTO dto) {
        Instant now = Instant.now();
        dto.setDateRented(now);
        dto = iterateItemsOnRent(dto);
        FilmRental filmRental = filmRentalMapper.toEntity(dto);
        filmRental = filmRentalRepository.save(filmRental);
        return filmRentalMapper.toDTO(filmRental);
    }

    @Override
    public FilmRentalDTO returnFilms(Long filmRentalId, Set<Long> items) {

        FilmRental filmRental = filmRentalRepository.findById(filmRentalId).orElseThrow();
        Instant dateReturned = Instant.now();
        BigDecimal totalSurcharge = BigDecimal.ZERO;

        for (Long itemId : items) {
            FilmRentalItem item = filmRental.getItems().stream().filter(x -> x.getId() == itemId).findFirst().get();

            if (item.getIsReturned()) {
                throw new RepeatedFilmReturnException(item.getFilm().getName());
            }

            long extraDays = ChronoUnit.DAYS.between(item.getDueDate(), dateReturned);
            BigDecimal surcharge = BigDecimal.ZERO;

            if (extraDays > 0) {
                surcharge = item.getFilm().getType().getSurchargePricing().getAmount()
                        .multiply(BigDecimal.valueOf(extraDays));
            }

            item.setSurcharge(surcharge);
            item.setExtraDays(extraDays);
            item.setIsReturned(true);
            item.setDateReturned(dateReturned);
            totalSurcharge = totalSurcharge.add(surcharge);
        }

        filmRental.setTotalSurcharge(totalSurcharge);
        filmRental = filmRentalRepository.save(filmRental);
        return filmRentalMapper.toDTO(filmRental);
    }

    private FilmRentalDTO iterateItemsOnRent(FilmRentalDTO dto) {

        BigDecimal totalFee = BigDecimal.ZERO;

        for (FilmRentalItemDTO item : dto.getItems()) {

            FilmDTO film = filmService.findOne(item.getFilmId()).orElseThrow(FilmNotFoundException::new);
            int daysRequested = item.getDaysRequested();
            int copiesLeft = film.getCopiesOnShelf();

            if (copiesLeft == 0) {
                throw new FilmOutOfStockException(film.getName());
            }

            FilmTypeDTO type = film.getType();
            BigDecimal initialFee = type.getInitialPricingAmount();
            int excessDays = daysRequested - type.getGracePeriod();
            item.setDueDate(dto.getDateRented().plus(daysRequested, ChronoUnit.DAYS));

            if (excessDays > 0) {
                BigDecimal extraCharge = type.getSurchargePricingAmount().multiply(BigDecimal.valueOf(excessDays));
                initialFee = initialFee.add(extraCharge);
            } else {
                // If excess days is negative, it means user is requesting for days less than
                // film's grace period, so grace period should be used to calculate due date
                // instead
                item.setDueDate(dto.getDateRented().plus(type.getGracePeriod(), ChronoUnit.DAYS));
            }

            item.setFee(initialFee);
            totalFee = totalFee.add(initialFee);
            film.setCopiesOnShelf(copiesLeft - 1);
            filmService.update(film);
        }

        dto.setTotalFee(totalFee);
        return dto;

    }

}
