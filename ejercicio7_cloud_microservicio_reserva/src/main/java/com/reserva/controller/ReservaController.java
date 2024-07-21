package com.reserva.controller;

import com.reserva.model.Reserva;
import com.reserva.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@CrossOrigin(origins = "*")  // Permitimos solicitudes desde cualquier origen
@RestController
@RequestMapping("/reservas")
public class ReservaController {
	
	// *********************************************************************************************************
	// IMPORTANTE!!!! PARA EVITAR ERRORES AL HACER LLAMADAS DESDE POSTMAN O SIMILAR
	//
	// Especificar rutas específicas como "/crear", esto elimina la ambigüedad y asegura que las solicitudes POST
	// sean manejadas correctamente por el método correspondiente en el controlador
	//
	// *********************************************************************************************************

	@Autowired
	private ReservaService reservaService;

	@Operation(summary = "Crear una nueva reserva", description = "Añade una nueva reserva a la base de datos")
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Reserva createReserva(
				@Valid @RequestBody Reserva reserva) {
		
		return reservaService.createReserva(reserva);
	}
	
	@Operation(summary = "Buscar reservas por nombre de hotel", description = "Busca reservas a partir del nombre del hotel proporcionado en la dirección")
    @GetMapping(value = "/hotel/{nombreHotel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reserva> getReservasByNombreHotel(
    			@Parameter(description = "Nombre del hotel cuyas reservas se desean buscar")
    			@PathVariable("nombreHotel") String nombreHotel) {
		
        return reservaService.findReservasByNombreHotel(nombreHotel);
    }
    
	@Operation(summary = "Buscar reserva por ID", description = "Busca una reserva a partir del ID proporcionado en la dirección")
	@GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Reserva getReservaById(
				@Parameter(description = "ID de la reserva a buscar") 
				@PathVariable("id") Long id) {
		
		return reservaService.findById(id);
	}
	
	@Operation(summary = "Actualizar una reserva", description = "Actualiza los detalles de una reserva existente")
    @PutMapping(value = "/actualizar/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Reserva updateReserva(
    			@Parameter(description = "ID de la reserva a actualizar") 
    			@PathVariable("id") Long id, @Valid @RequestBody Reserva reserva) {
		
        return reservaService.updateReserva(id, reserva);
    }

    @Operation(summary = "Eliminar una reserva", description = "Elimina una reserva a partir del ID proporcionado")
    @DeleteMapping(value = "/eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteReserva(
    			@Parameter(description = "ID de la reserva a eliminar") 
    			@PathVariable("id") Long id) {
    	
        reservaService.deleteReserva(id);
    }
    
    @Operation(summary = "Verificar si existen reservas activas para un vuelo", description = "Verifica si existen reservas activas asociadas a un vuelo dado su ID")
    @GetMapping(value = "/existeReservaPorVuelo/{idVuelo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean existeReservaPorVuelo(
    			@Parameter(description = "ID del vuelo a verificar") @PathVariable("idVuelo") Long idVuelo) {
    	
        return reservaService.existeReservaPorVuelo(idVuelo);
    }
    
}