package com.casumo.videorental.service.mapping;

import com.casumo.videorental.entity.Customer;
import com.casumo.videorental.service.dto.CustomerDTO;
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
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer e) {
        if ( e == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setAddress( e.getAddress() );
        customerDTO.setEmail( e.getEmail() );
        customerDTO.setFirstName( e.getFirstName() );
        customerDTO.setId( e.getId() );
        customerDTO.setLastName( e.getLastName() );
        customerDTO.setName( e.getName() );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setAddress( dto.getAddress() );
        customer.setEmail( dto.getEmail() );
        customer.setFirstName( dto.getFirstName() );
        customer.setId( dto.getId() );
        customer.setLastName( dto.getLastName() );
        customer.setName( dto.getName() );

        return customer;
    }

    @Override
    public List<CustomerDTO> toDTO(List<Customer> e) {
        if ( e == null ) {
            return null;
        }

        List<CustomerDTO> list = new ArrayList<CustomerDTO>( e.size() );
        for ( Customer customer : e ) {
            list.add( toDTO( customer ) );
        }

        return list;
    }

    @Override
    public List<Customer> toEntity(List<CustomerDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( dto.size() );
        for ( CustomerDTO customerDTO : dto ) {
            list.add( toEntity( customerDTO ) );
        }

        return list;
    }
}
