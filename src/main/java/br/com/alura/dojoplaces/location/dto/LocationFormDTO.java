package br.com.alura.dojoplaces.location.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class LocationFormDTO implements Serializable {
    @NotEmpty(message = "Nome não pode ser vazio.")
    @Length(min = 1, max = 100, message = "Nome não pode ser maior que 100 caracteres.")
    private String name;

    @NotEmpty(message = "Código não pode ser vazio.")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message =  "Código não deve conter caracteres especiais.")
    private String code;

    @NotEmpty(message = "Bairro não pode ser vazio.")
    @Length(min = 1, max = 100, message = "Bairro não pode ser maior que 100 caracteres.")
    private String neighborhood;

    @NotEmpty(message = "Cidade não pode ser vazio.")
    @Length(min = 1, max = 100, message = "Cidade não pode ser maior que 100 caracteres.")
    private String city;

    public LocationFormDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
