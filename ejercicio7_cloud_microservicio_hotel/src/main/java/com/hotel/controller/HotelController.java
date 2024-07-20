package com.hotel.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.hotel.model.Hotel;
import com.hotel.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")  // Permitimos solicitudes desde cualquier origen
@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Operation(summary = "Obtener todos los hoteles disponibles", description = "Devuelve la lista de todos los hoteles disponibles")
    @GetMapping(value = "/disponibles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> getHotelesDisponibles() {
        return hotelService.findAllAvailable();
    }

    @Operation(summary = "Buscar hotel por nombre", description = "Busca un hotel a partir del nombre proporcionado en la dirección")
    @GetMapping(value = "/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelByNombre(@Parameter(description = "Nombre del hotel a buscar") @PathVariable("nombre") String nombre) {
        return hotelService.findByName(nombre).orElse(null);
    }

    @Operation(summary = "Crear un nuevo hotel", description = "Añade un nuevo hotel a la base de datos")
    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel createHotel(@Valid @RequestBody Hotel hotel) {
    	Optional<Hotel> savedHotel = hotelService.saveHotel(hotel);
        if (savedHotel.isPresent()) {
            return savedHotel.get();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se pudo crear el hotel. Ya existe un hotel con el nombre: " + hotel.getNombre());
        }
    }

    @Operation(summary = "Buscar hotel por ID", description = "Busca un hotel a partir del ID proporcionado en la dirección")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelById(@Parameter(description = "ID del hotel a buscar") @PathVariable("id") Long id) {
        return hotelService.findById(id).orElse(null);
    }
    
    @Operation(summary = "Actualizar un hotel", description = "Actualiza los detalles de un hotel existente")
    @PutMapping(value = "/actualizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel updateHotel(@Parameter(description = "ID del hotel a actualizar") @PathVariable("id") Long id, @Valid @RequestBody Hotel hotel) {
        return hotelService.updateHotel(id, hotel).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado con ID: " + id));
    }

    @Operation(summary = "Eliminar un hotel", description = "Elimina un hotel a partir del ID proporcionado")
    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteHotel(@Parameter(description = "ID del hotel a eliminar") @PathVariable("id") Long id) {
        if (!hotelService.deleteHotel(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado con ID: " + id);
        }
    }
	
}
