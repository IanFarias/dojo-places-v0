package br.com.alura.dojoplaces.location;

import br.com.alura.dojoplaces.location.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public void createLocal(LocationFormDTO dto) throws Exception {
        var alreadyExists = this.repository.existsLocationByCode(dto.getCode());

        if (alreadyExists) {
            throw new Exception("Already exists.");
        }

        Location location = LocationMapper.toLocation(dto);
        this.repository.save(location);
    }

    public LocationEditFormDTO findLocationById(Long id) throws Exception {
        var location = this.findById(id);

        return LocationMapper.toLocationEditForm(location);
    }

    public void update(LocationEditFormDTO form) throws Exception {
        var location = this.findById(form.getId());

        location.update(form);

        this.repository.save(location);
    }

    public void delete(Long id) throws Exception {
        var location = this.findById(id);

        this.repository.delete(location);
    }

    public List<LocationListDTO> listAll() {
        return repository.findAll()
                .stream()
                .map(LocationMapper::toLocationListDTO)
                .toList();
    }

    private Location findById(Long id) throws Exception {
       return this.repository.findById(id).orElseThrow(Exception::new);
    }
}
