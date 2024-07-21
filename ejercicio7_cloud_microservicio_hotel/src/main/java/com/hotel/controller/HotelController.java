package com.hotel.controller;

import java.util.List;
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
import com.hotel.model.Hotel;
import com.hotel.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")  // Permitimos solicitudes desde cualquier origen
@RestController
@RequestMapping("/hoteles")
public class HotelController {
	
	// *********************************************************************************************************
	// IMPORTANTE!!!! PARA EVITAR ERRORES AL HACER LLAMADAS DESDE POSTMAN O SIMILAR
	//
	// Especificar rutas específicas como "/crear", esto elimina la ambigüedad y asegura que las solicitudes POST
	// sean manejadas correctamente por el método correspondiente en el controlador
	//
	// *********************************************************************************************************

    @Autowired
    private HotelService hotelService;

    @Operation(summary = "Obtener todos los hoteles disponibles", description = "Devuelve la lista de todos los hoteles disponibles")
    @GetMapping(value = "/disponibles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Hotel> getHotelesDisponibles() {
    	
        return hotelService.findAllAvailable();
    }

    @Operation(summary = "Buscar hotel por nombre", description = "Busca un hotel a partir del nombre proporcionado en la dirección")
    @GetMapping(value = "/nombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelByNombre(
    			@Parameter(description = "Nombre del hotel a buscar") @PathVariable("nombre") String nombre) {
    	
    	return hotelService.findByName(nombre);
    }
    
    @Operation(summary = "Obtener ID de hotel por nombre", description = "Obtiene el ID de un hotel a partir del nombre proporcionado")
    @GetMapping(value = "/idPorNombre/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getHotelIdByName(
            	@Parameter(description = "Nombre del hotel para obtener el ID") @PathVariable("nombre") String nombre) {
        
    	return hotelService.findIdByName(nombre);
    }

    @Operation(summary = "Crear un nuevo hotel", description = "Añade un nuevo hotel a la base de datos")
    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel createHotel(	
    			@Valid @RequestBody Hotel hotel) {
    	
    	return hotelService.saveHotel(hotel);
    }

    @Operation(summary = "Buscar hotel por ID", description = "Busca un hotel a partir del ID proporcionado en la dirección")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel getHotelById(
    			@Parameter(description = "ID del hotel a buscar") @PathVariable("id") Long id) {
        
    	return hotelService.findById(id);
    }
    
    @Operation(summary = "Actualizar un hotel", description = "Actualiza los detalles de un hotel existente")
    @PutMapping(value = "/actualizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Hotel updateHotel(
    			@Parameter(description = "ID del hotel a actualizar") @PathVariable("id") Long id, 
    			@Valid @RequestBody Hotel hotel) {
        
    	return hotelService.updateHotel(id, hotel);
    }

    @Operation(summary = "Eliminar un hotel", description = "Elimina un hotel a partir del ID proporcionado")
    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteHotel(
    			@Parameter(description = "ID del hotel a eliminar") @PathVariable("id") Long id) {
    	
        hotelService.deleteHotel(id);
    }
	
}
