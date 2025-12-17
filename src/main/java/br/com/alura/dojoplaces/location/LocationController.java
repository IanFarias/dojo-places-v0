package br.com.alura.dojoplaces.location;

import br.com.alura.dojoplaces.location.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("/location/create")
    public String createLocation(LocationFormDTO form, Model model) {
        model.addAttribute("locationFormDTO", form);
        return "location/form";
    }

    @PostMapping("/location/create")
    public String saveLocation(@Valid LocationFormDTO form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return createLocation(form, model);
        }

        Location location = LocationMapper.toLocation(form);
        locationRepository.save(location);

        return "redirect:/";
    }

    @GetMapping("/location/edit/{id}")
    public String edit(@PathVariable Long id, Model model) throws Exception {

        var location = locationRepository.findById(id).orElseThrow(Exception::new);

        model.addAttribute("locationEditFormDTO", LocationMapper.toLocationEditForm(location));

        return "location/editForm";
    }

    @PostMapping("/location/edit/{id}")
    public String update(@Valid LocationEditFormDTO form, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            System.out.println("Validation errors: " + result.getAllErrors());
            return edit(form.getId(), model);
        }
        var location = this.locationRepository.findById(form.getId()).orElseThrow(Exception::new);

        location.update(form);

        this.locationRepository.save(location);

        return edit(form.getId(), model);
    }

    @DeleteMapping("/location/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        var location = this.locationRepository.findById(id).orElseThrow(Exception::new);

        this.locationRepository.delete(location);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public String listLocations(Model model) {
        List<LocationListDTO> locationListDTOS = locationRepository.findAll()
                .stream()
                .map(LocationMapper::toLocationListDTO)
                .toList();

        model.addAttribute("locations", locationListDTOS);

        return "location/list";
    }
}
