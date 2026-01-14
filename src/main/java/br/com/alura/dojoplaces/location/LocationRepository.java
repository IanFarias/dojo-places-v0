package br.com.alura.dojoplaces.location;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsLocationByCode(String code);
}
