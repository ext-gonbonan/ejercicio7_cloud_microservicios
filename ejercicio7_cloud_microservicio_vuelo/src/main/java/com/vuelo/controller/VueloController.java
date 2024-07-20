package com.vuelo.controller;

import com.vuelo.model.Vuelo;
import com.vuelo.service.VueloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService vueloService;

    @Operation(summary = "Obtener todos los vuelos disponibles", description = "Devuelve la lista de todos los vuelos disponibles con suficientes plazas")
    @GetMapping(value = "/disponibles/{plazas}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vuelo> getVuelosDisponibles(@Parameter(description = "Número de plazas requeridas") @PathVariable("plazas") Integer plazas) {
        return vueloService.findAllAvailable(plazas);
    }

    @Operation(summary = "Actualizar plazas disponibles de un vuelo", description = "Actualiza las plazas disponibles de un vuelo a partir del ID proporcionado")
    @PutMapping(value = "/{idVuelo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vuelo updateVueloPlazas(@Parameter(description = "ID del vuelo a actualizar") @PathVariable("idVuelo") Long idVuelo,
                                   @Parameter(description = "Número de plazas reservadas") @RequestParam Integer plazasReservadas) {
        return vueloService.updateVueloPlazas(idVuelo, plazasReservadas).orElse(null);
    }

    @Operation(summary = "Crear un nuevo vuelo", description = "Añade un nuevo vuelo a la base de datos")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Vuelo createVuelo(@RequestBody Vuelo vuelo) {
        return vueloService.saveVuelo(vuelo);
    }

    @Operation(summary = "Buscar vuelo por ID", description = "Busca un vuelo a partir del ID proporcionado en la dirección")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vuelo getVueloById(@Parameter(description = "ID del vuelo a buscar") @PathVariable("id") Long id) {
        return vueloService.findById(id).orElse(null);
    }
    
}