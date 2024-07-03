package com.casumo.videorental.service.mapping;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import com.casumo.videorental.entity.Customer;
import com.casumo.videorental.service.dto.CustomerDTO;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {})
public interface CustomerMapper extends EntityMapper<Customer, CustomerDTO> {

}
