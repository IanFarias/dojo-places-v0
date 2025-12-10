package br.com.alura.dojoplaces.location.dto;


public record LocationListDTO(
        Long id,
        String name,
        String code,
        String createdAt,
        String updatedAt
) {
}
