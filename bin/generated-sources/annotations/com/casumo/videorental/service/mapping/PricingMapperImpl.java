package com.casumo.videorental.service.mapping;

import com.casumo.videorental.entity.Pricing;
import com.casumo.videorental.service.dto.PricingDTO;
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
public class PricingMapperImpl implements PricingMapper {

    @Override
    public PricingDTO toDTO(Pricing e) {
        if ( e == null ) {
            return null;
        }

        PricingDTO pricingDTO = new PricingDTO();

        pricingDTO.setAmount( e.getAmount() );
        pricingDTO.setId( e.getId() );
        pricingDTO.setName( e.getName() );

        return pricingDTO;
    }

    @Override
    public Pricing toEntity(PricingDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pricing pricing = new Pricing();

        pricing.setAmount( dto.getAmount() );
        pricing.setId( dto.getId() );
        pricing.setName( dto.getName() );

        return pricing;
    }

    @Override
    public List<PricingDTO> toDTO(List<Pricing> e) {
        if ( e == null ) {
            return null;
        }

        List<PricingDTO> list = new ArrayList<PricingDTO>( e.size() );
        for ( Pricing pricing : e ) {
            list.add( toDTO( pricing ) );
        }

        return list;
    }

    @Override
    public List<Pricing> toEntity(List<PricingDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Pricing> list = new ArrayList<Pricing>( dto.size() );
        for ( PricingDTO pricingDTO : dto ) {
            list.add( toEntity( pricingDTO ) );
        }

        return list;
    }
}
