package com.casumo.videorental.service.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String name;

    private String email;

    private String address;
}
