package br.com.alura.dojoplaces.location.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class LocationEditFormDTO implements Serializable {
    private Long id;

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

    @Length(min = 8, max = 8, message = "CEP deve possuir 8 caracteres.")
    private String cep;

    public LocationEditFormDTO() {
    }

    public LocationEditFormDTO(Long id, String name, String code, String cep, String neighborhood, String city) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.cep = cep;
        this.neighborhood = neighborhood;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCep() { return this.cep = cep; }

    public void setCep(String cep) { this.cep = cep; }
}
