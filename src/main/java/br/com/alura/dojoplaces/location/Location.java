package br.com.alura.dojoplaces.location;

import br.com.alura.dojoplaces.location.dto.LocationEditFormDTO;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    private String neighborhood;

    private String city;

    private String cep;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public Location() {

    }

    public Location(String name, String code, String neighborhood, String city, String cep, LocalDate createdAt, LocalDate updatedAt) {
        this.name = name;
        this.code = code;
        this.neighborhood = neighborhood;
        this.city = city;
        this.cep = cep;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(LocationEditFormDTO location) {
        this.setCity(location.getCity());
        this.setCode(location.getCode());
        this.setName(location.getName());
        this.setNeighborhood(location.getNeighborhood());
        this.setCep(location.getCep());
        this.setUpdatedAt(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
