
package com.casumo.videorental.resource;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casumo.videorental.service.FilmRentalService;
import com.casumo.videorental.service.dto.FilmRentalDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
public class FilmRentalResource {

    private final FilmRentalService filmRentalService;

    @PostMapping(value = "/film-rentals")
    public ResponseEntity<FilmRentalDTO> rentFilm(@RequestBody FilmRentalDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmRentalService.rentFilms(dto));
    }

    @PutMapping(value = "/film-rentals/{filmRentalId}/return")
    public ResponseEntity<FilmRentalDTO> returnFilm(@PathVariable(name = "filmRentalId") Long filmRentalId,
            @RequestParam(name = "items") Set<Long> items) {
        return ResponseEntity.ok(filmRentalService.returnFilms(filmRentalId, items));
    }

}
