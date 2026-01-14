package br.com.alura.dojoplaces.location;

import br.com.alura.dojoplaces.location.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
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

        try {
            this.locationService.createLocal(form);

            return "redirect:/";
        }catch (Exception e) {
            result.addError(new ObjectError("codeExists", "Código já está cadastrado."));
            return createLocation(form, model);
        }
    }

    @GetMapping("/location/edit/{id}")
    public String edit(@PathVariable Long id, Model model) throws Exception {
        var location = this.locationService.findLocationById(id);

        model.addAttribute("locationEditFormDTO", location);

        return "location/editForm";
    }

    @PostMapping("/location/edit/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("locationEditFormDTO") LocationEditFormDTO form,
            BindingResult result,
            Model model
    ) throws Exception {

        if (result.hasErrors()) {
            return "location/editForm";
        }

        this.locationService.update(form);

        return "redirect:/location/edit/" + id;
    }

    @DeleteMapping("/location/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) throws Exception {
        this.locationService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public String listLocations(Model model) {
        List<LocationListDTO> locationListDTOS = this.locationService.listAll();

        model.addAttribute("locations", locationListDTOS);

        return "location/list";
    }
}
