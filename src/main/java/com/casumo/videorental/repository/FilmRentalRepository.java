package com.casumo.videorental.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.casumo.videorental.entity.FilmRental;


public interface FilmRentalRepository extends JpaRepository<FilmRental, Long> {

}

