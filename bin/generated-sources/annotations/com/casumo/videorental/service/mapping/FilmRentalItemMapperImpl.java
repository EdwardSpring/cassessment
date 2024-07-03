package com.casumo.videorental.service.mapping;

import com.casumo.videorental.entity.Film;
import com.casumo.videorental.entity.FilmRental;
import com.casumo.videorental.entity.FilmRentalItem;
import com.casumo.videorental.service.dto.FilmRentalItemDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-03T23:24:35+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class FilmRentalItemMapperImpl implements FilmRentalItemMapper {

    @Override
    public List<FilmRentalItemDTO> toDTO(List<FilmRentalItem> e) {
        if ( e == null ) {
            return null;
        }

        List<FilmRentalItemDTO> list = new ArrayList<FilmRentalItemDTO>( e.size() );
        for ( FilmRentalItem filmRentalItem : e ) {
            list.add( toDTO( filmRentalItem ) );
        }

        return list;
    }

    @Override
    public List<FilmRentalItem> toEntity(List<FilmRentalItemDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<FilmRentalItem> list = new ArrayList<FilmRentalItem>( dto.size() );
        for ( FilmRentalItemDTO filmRentalItemDTO : dto ) {
            list.add( toEntity( filmRentalItemDTO ) );
        }

        return list;
    }

    @Override
    public FilmRentalItemDTO toDTO(FilmRentalItem e) {
        if ( e == null ) {
            return null;
        }

        FilmRentalItemDTO filmRentalItemDTO = new FilmRentalItemDTO();

        filmRentalItemDTO.setFilmRentalId( eFilmRentalId( e ) );
        filmRentalItemDTO.setFilmId( eFilmId( e ) );
        filmRentalItemDTO.setDateReturned( e.getDateReturned() );
        filmRentalItemDTO.setDaysRequested( e.getDaysRequested() );
        filmRentalItemDTO.setDueDate( e.getDueDate() );
        filmRentalItemDTO.setExtraDays( e.getExtraDays() );
        filmRentalItemDTO.setFee( e.getFee() );
        filmRentalItemDTO.setId( e.getId() );
        filmRentalItemDTO.setIsReturned( e.getIsReturned() );
        filmRentalItemDTO.setSurcharge( e.getSurcharge() );

        return filmRentalItemDTO;
    }

    @Override
    public FilmRentalItem toEntity(FilmRentalItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FilmRentalItem filmRentalItem = new FilmRentalItem();

        filmRentalItem.setFilmRental( filmRentalItemDTOToFilmRental( dto ) );
        filmRentalItem.setFilm( filmRentalItemDTOToFilm( dto ) );
        filmRentalItem.setDateReturned( dto.getDateReturned() );
        filmRentalItem.setDaysRequested( dto.getDaysRequested() );
        filmRentalItem.setDueDate( dto.getDueDate() );
        filmRentalItem.setExtraDays( dto.getExtraDays() );
        filmRentalItem.setFee( dto.getFee() );
        filmRentalItem.setId( dto.getId() );
        filmRentalItem.setIsReturned( dto.getIsReturned() );
        filmRentalItem.setSurcharge( dto.getSurcharge() );

        return filmRentalItem;
    }

    private Long eFilmRentalId(FilmRentalItem filmRentalItem) {
        if ( filmRentalItem == null ) {
            return null;
        }
        FilmRental filmRental = filmRentalItem.getFilmRental();
        if ( filmRental == null ) {
            return null;
        }
        Long id = filmRental.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long eFilmId(FilmRentalItem filmRentalItem) {
        if ( filmRentalItem == null ) {
            return null;
        }
        Film film = filmRentalItem.getFilm();
        if ( film == null ) {
            return null;
        }
        Long id = film.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected FilmRental filmRentalItemDTOToFilmRental(FilmRentalItemDTO filmRentalItemDTO) {
        if ( filmRentalItemDTO == null ) {
            return null;
        }

        FilmRental filmRental = new FilmRental();

        filmRental.setId( filmRentalItemDTO.getFilmRentalId() );

        return filmRental;
    }

    protected Film filmRentalItemDTOToFilm(FilmRentalItemDTO filmRentalItemDTO) {
        if ( filmRentalItemDTO == null ) {
            return null;
        }

        Film film = new Film();

        film.setId( filmRentalItemDTO.getFilmId() );

        return film;
    }
}
