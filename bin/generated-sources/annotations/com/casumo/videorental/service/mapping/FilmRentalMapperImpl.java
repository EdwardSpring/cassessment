package com.casumo.videorental.service.mapping;

import com.casumo.videorental.entity.Customer;
import com.casumo.videorental.entity.FilmRental;
import com.casumo.videorental.entity.FilmRentalItem;
import com.casumo.videorental.service.dto.FilmRentalDTO;
import com.casumo.videorental.service.dto.FilmRentalItemDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-03T23:24:36+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class FilmRentalMapperImpl implements FilmRentalMapper {

    @Autowired
    private FilmRentalItemMapper filmRentalItemMapper;

    @Override
    public List<FilmRentalDTO> toDTO(List<FilmRental> e) {
        if ( e == null ) {
            return null;
        }

        List<FilmRentalDTO> list = new ArrayList<FilmRentalDTO>( e.size() );
        for ( FilmRental filmRental : e ) {
            list.add( toDTO( filmRental ) );
        }

        return list;
    }

    @Override
    public List<FilmRental> toEntity(List<FilmRentalDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<FilmRental> list = new ArrayList<FilmRental>( dto.size() );
        for ( FilmRentalDTO filmRentalDTO : dto ) {
            list.add( toEntity( filmRentalDTO ) );
        }

        return list;
    }

    @Override
    public FilmRentalDTO toDTO(FilmRental e) {
        if ( e == null ) {
            return null;
        }

        FilmRentalDTO filmRentalDTO = new FilmRentalDTO();

        filmRentalDTO.setCustomerId( eCustomerId( e ) );
        filmRentalDTO.setCustomerName( eCustomerName( e ) );
        filmRentalDTO.setDateRented( e.getDateRented() );
        filmRentalDTO.setId( e.getId() );
        filmRentalDTO.setItems( filmRentalItemSetToFilmRentalItemDTOSet( e.getItems() ) );
        filmRentalDTO.setTotalFee( e.getTotalFee() );
        filmRentalDTO.setTotalSurcharge( e.getTotalSurcharge() );

        return filmRentalDTO;
    }

    @Override
    public FilmRental toEntity(FilmRentalDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FilmRental filmRental = new FilmRental();

        filmRental.setCustomer( filmRentalDTOToCustomer( dto ) );
        filmRental.setDateRented( dto.getDateRented() );
        filmRental.setId( dto.getId() );
        filmRental.setItems( filmRentalItemDTOSetToFilmRentalItemSet( dto.getItems() ) );
        filmRental.setTotalFee( dto.getTotalFee() );
        filmRental.setTotalSurcharge( dto.getTotalSurcharge() );

        return filmRental;
    }

    private Long eCustomerId(FilmRental filmRental) {
        if ( filmRental == null ) {
            return null;
        }
        Customer customer = filmRental.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String eCustomerName(FilmRental filmRental) {
        if ( filmRental == null ) {
            return null;
        }
        Customer customer = filmRental.getCustomer();
        if ( customer == null ) {
            return null;
        }
        String name = customer.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Set<FilmRentalItemDTO> filmRentalItemSetToFilmRentalItemDTOSet(Set<FilmRentalItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<FilmRentalItemDTO> set1 = new HashSet<FilmRentalItemDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( FilmRentalItem filmRentalItem : set ) {
            set1.add( filmRentalItemMapper.toDTO( filmRentalItem ) );
        }

        return set1;
    }

    protected Customer filmRentalDTOToCustomer(FilmRentalDTO filmRentalDTO) {
        if ( filmRentalDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( filmRentalDTO.getCustomerId() );

        return customer;
    }

    protected Set<FilmRentalItem> filmRentalItemDTOSetToFilmRentalItemSet(Set<FilmRentalItemDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<FilmRentalItem> set1 = new HashSet<FilmRentalItem>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( FilmRentalItemDTO filmRentalItemDTO : set ) {
            set1.add( filmRentalItemMapper.toEntity( filmRentalItemDTO ) );
        }

        return set1;
    }
}
