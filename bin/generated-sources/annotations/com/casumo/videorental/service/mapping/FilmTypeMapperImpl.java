package com.casumo.videorental.service.mapping;

import com.casumo.videorental.entity.FilmType;
import com.casumo.videorental.entity.Pricing;
import com.casumo.videorental.service.dto.FilmTypeDTO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-03T23:24:36+0100",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.39.0.v20240620-1855, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class FilmTypeMapperImpl implements FilmTypeMapper {

    @Override
    public FilmType toEntity(FilmTypeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FilmType filmType = new FilmType();

        filmType.setGracePeriod( dto.getGracePeriod() );
        filmType.setId( dto.getId() );
        filmType.setName( dto.getName() );

        return filmType;
    }

    @Override
    public List<FilmTypeDTO> toDTO(List<FilmType> e) {
        if ( e == null ) {
            return null;
        }

        List<FilmTypeDTO> list = new ArrayList<FilmTypeDTO>( e.size() );
        for ( FilmType filmType : e ) {
            list.add( toDTO( filmType ) );
        }

        return list;
    }

    @Override
    public List<FilmType> toEntity(List<FilmTypeDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<FilmType> list = new ArrayList<FilmType>( dto.size() );
        for ( FilmTypeDTO filmTypeDTO : dto ) {
            list.add( toEntity( filmTypeDTO ) );
        }

        return list;
    }

    @Override
    public FilmTypeDTO toDTO(FilmType e) {
        if ( e == null ) {
            return null;
        }

        FilmTypeDTO filmTypeDTO = new FilmTypeDTO();

        filmTypeDTO.setInitialPricingId( eInitialPricingId( e ) );
        filmTypeDTO.setSurchargePricingId( eSurchargePricingId( e ) );
        filmTypeDTO.setInitialPricingAmount( eInitialPricingAmount( e ) );
        filmTypeDTO.setSurchargePricingAmount( eSurchargePricingAmount( e ) );
        filmTypeDTO.setGracePeriod( e.getGracePeriod() );
        filmTypeDTO.setId( e.getId() );
        filmTypeDTO.setName( e.getName() );

        return filmTypeDTO;
    }

    private Long eInitialPricingId(FilmType filmType) {
        if ( filmType == null ) {
            return null;
        }
        Pricing initialPricing = filmType.getInitialPricing();
        if ( initialPricing == null ) {
            return null;
        }
        Long id = initialPricing.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long eSurchargePricingId(FilmType filmType) {
        if ( filmType == null ) {
            return null;
        }
        Pricing surchargePricing = filmType.getSurchargePricing();
        if ( surchargePricing == null ) {
            return null;
        }
        Long id = surchargePricing.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private BigDecimal eInitialPricingAmount(FilmType filmType) {
        if ( filmType == null ) {
            return null;
        }
        Pricing initialPricing = filmType.getInitialPricing();
        if ( initialPricing == null ) {
            return null;
        }
        BigDecimal amount = initialPricing.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }

    private BigDecimal eSurchargePricingAmount(FilmType filmType) {
        if ( filmType == null ) {
            return null;
        }
        Pricing surchargePricing = filmType.getSurchargePricing();
        if ( surchargePricing == null ) {
            return null;
        }
        BigDecimal amount = surchargePricing.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }
}
