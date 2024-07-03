package com.casumo.videorental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casumo.videorental.entity.Film;

public interface FilmRepository extends JpaRepository<Film, Long> {

}
