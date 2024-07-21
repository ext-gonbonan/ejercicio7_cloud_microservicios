package com.vuelo.controller;

import com.vuelo.model.Vuelo;
import com.vuelo.service.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

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
import java.util.List;

@CrossOrigin(origins = "*")  // Permitimos solicitudes desde cualquier origen
@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @Operation(summary = "Obtener todos los vuelos disponibles", description = "Devuelve la lista de todos los vuelos disponibles con suficientes plazas")
    @GetMapping(value = "/disponibles/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vuelo> getVuelosDisponibles(
    			@Parameter(description = "Número de plazas requeridas") 
    			@PathVariable("plazas") Integer plazas) {
        
    	return vueloService.findAllAvailable(plazas);
    }

    @Operation(summary = "Actualizar plazas disponibles de un vuelo", description = "Actualiza las plazas disponibles de un vuelo a partir del ID y las plazas reservadas proporcionados en la URL")
    @PutMapping(value = "/{idVuelo}/{plazasReservadas}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vuelo updateVueloPlazas(
            	@Parameter(description = "ID del vuelo a actualizar") @PathVariable("idVuelo") Long idVuelo,
            	@Parameter(description = "Número de plazas reservadas") @PathVariable("plazasReservadas") Integer plazasReservadas) {
        
    	return vueloService.updateVueloPlazas(idVuelo, plazasReservadas);
    }

    @Operation(summary = "Crear un nuevo vuelo", description = "Añade un nuevo vuelo a la base de datos")
    @PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vuelo createVuelo(
    			@Valid @RequestBody Vuelo vuelo) {
    	
        return vueloService.saveVuelo(vuelo);
    }

    @Operation(summary = "Buscar vuelo por ID", description = "Busca un vuelo a partir del ID proporcionado en la dirección")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vuelo getVueloById(
    			@Parameter(description = "ID del vuelo a buscar") @PathVariable("id") Long id) {
    	
        return vueloService.findById(id);
    }
    
    @Operation(summary = "Actualizar un vuelo", description = "Actualiza los detalles de un vuelo existente")
    @PutMapping(value = "/actualizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vuelo updateVuelo(
    			@Parameter(description = "ID del vuelo a actualizar") @PathVariable("id") Long id, 
    			@Valid @RequestBody Vuelo vuelo) {
        
    	return vueloService.updateVuelo(id, vuelo);
    }

    @Operation(summary = "Eliminar un vuelo", description = "Elimina un vuelo a partir del ID proporcionado")
    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteVuelo(
    			@Parameter(description = "ID del vuelo a eliminar") @PathVariable("id") Long id) {
        
    	vueloService.deleteVuelo(id);
    }
    
}
    
