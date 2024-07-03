package com.casumo.videorental.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {

    private Long id;

    private String name;

    private FilmTypeDTO type;

    private Long typeId;

    private Integer copies;

    private Integer copiesOnShelf;
}
