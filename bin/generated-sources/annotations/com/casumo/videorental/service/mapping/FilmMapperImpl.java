package com.casumo.videorental.service.mapping;

import com.casumo.videorental.entity.Film;
import com.casumo.videorental.service.dto.FilmDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-03T23:24:36+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class FilmMapperImpl implements FilmMapper {

    @Autowired
    private FilmTypeMapper filmTypeMapper;

    @Override
    public FilmDTO toDTO(Film e) {
        if ( e == null ) {
            return null;
        }

        FilmDTO filmDTO = new FilmDTO();

        filmDTO.setCopies( e.getCopies() );
        filmDTO.setCopiesOnShelf( e.getCopiesOnShelf() );
        filmDTO.setId( e.getId() );
        filmDTO.setName( e.getName() );
        filmDTO.setType( filmTypeMapper.toDTO( e.getType() ) );

        return filmDTO;
    }

    @Override
    public Film toEntity(FilmDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Film film = new Film();

        film.setCopies( dto.getCopies() );
        film.setCopiesOnShelf( dto.getCopiesOnShelf() );
        film.setId( dto.getId() );
        film.setName( dto.getName() );
        film.setType( filmTypeMapper.toEntity( dto.getType() ) );

        return film;
    }

    @Override
    public List<FilmDTO> toDTO(List<Film> e) {
        if ( e == null ) {
            return null;
        }

        List<FilmDTO> list = new ArrayList<FilmDTO>( e.size() );
        for ( Film film : e ) {
            list.add( toDTO( film ) );
        }

        return list;
    }

    @Override
    public List<Film> toEntity(List<FilmDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Film> list = new ArrayList<Film>( dto.size() );
        for ( FilmDTO filmDTO : dto ) {
            list.add( toEntity( filmDTO ) );
        }

        return list;
    }
}
