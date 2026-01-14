package br.com.alura.dojoplaces.location;

import br.com.alura.dojoplaces.location.dto.*;
import br.com.alura.dojoplaces.utils.DateFormatter;

import java.time.LocalDate;

public class LocationMapper {
    public static Location toLocation(final LocationFormDTO location) {
        return new Location(
                location.getName(),
                location.getCode(),
                location.getNeighborhood(),
                location.getCity(),
                location.getCep(),
                LocalDate.now(),
                LocalDate.now()
        );
    }

    public static LocationListDTO toLocationListDTO(final Location location) {
        return new LocationListDTO(
                location.getId(),
                location.getName(),
                location.getCode(),
                DateFormatter.formattedDate(location.getCreatedAt()),
                DateFormatter.formattedDate(location.getUpdatedAt())
        );
    }

    public static LocationEditFormDTO toLocationEditForm(Location location) {
        return new LocationEditFormDTO(
                location.getId(),
                location.getName(),
                location.getCode(),
                location.getCep(),
                location.getNeighborhood(),
                location.getCity()
        );
    }
}
