package com.casumo.videorental.entity;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmRentalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer daysRequested;

    private BigDecimal fee;

    private Instant dueDate;

    private Instant dateReturned;

    private Long extraDays;

    private BigDecimal surcharge;

    @ManyToOne
    private Film film;

    @ManyToOne
    @ToString.Exclude
    private FilmRental filmRental;

    private Boolean isReturned;

}
