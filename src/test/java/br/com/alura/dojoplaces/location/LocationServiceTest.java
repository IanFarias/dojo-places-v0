package br.com.alura.dojoplaces.location;

import br.com.alura.dojoplaces.location.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepository repository;

    @InjectMocks
    private LocationService locationService;

    private LocationFormDTO formDTO;
    private Location location;

    @BeforeEach
    void setUp() {
        formDTO = new LocationFormDTO();
        formDTO.setCode("TESTE");

        location = mock(Location.class);
    }

    @Test
    void createLocal_shouldCreateNewLocationWhenCodeNotExists() throws Exception {
        when(repository.existsLocationByCode("TESTE")).thenReturn(false);

        locationService.createLocal(formDTO);

        verify(repository).existsLocationByCode("TESTE");
        verify(repository).save(any(Location.class));
    }

    @Test
    void createLocal_shouldThrowExceptionWhenCodeAlreadyExists() {
        when(repository.existsLocationByCode("TESTE")).thenReturn(true);

        Exception ex = assertThrows(Exception.class, () -> locationService.createLocal(formDTO));

        assertEquals("Already exists.", ex.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void update_shouldThrowExceptionWhenUpdatingNonExistingLocation() {
        LocationEditFormDTO editForm = new LocationEditFormDTO();
        editForm.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                Exception.class,
                () -> locationService.update(editForm)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void shouldDeleteLocationWhenExists() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.of(location));

        locationService.delete(1L);

        verify(repository).delete(location);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingLocation() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                Exception.class,
                () -> locationService.delete(1L)
        );

        verify(repository, never()).delete(any());
    }

    @Test
    void shouldListAllLocations() {
        Location location1 = mock(Location.class);
        Location location2 = mock(Location.class);

        when(location1.getCreatedAt()).thenReturn(LocalDate.now());
        when(location1.getUpdatedAt()).thenReturn(LocalDate.now());

        when(location2.getCreatedAt()).thenReturn(LocalDate.now());
        when(location2.getUpdatedAt()).thenReturn(LocalDate.now());

        when(repository.findAll()).thenReturn(List.of(location1, location2));

        List<LocationListDTO> result = locationService.listAll();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }
}