package com.reserva.controller;

import com.reserva.model.Reserva;
import com.reserva.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@Operation(summary = "Crear una nueva reserva", description = "Añade una nueva reserva a la base de datos")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Reserva createReserva(@RequestBody Reserva reserva) {
		return reservaService.createReserva(reserva);
	}

	@Operation(summary = "Buscar reservas por ID de hotel", description = "Busca reservas a partir del ID del hotel proporcionado en la dirección")
	@GetMapping(value = "/hotel/{idHotel}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Reserva> getReservasByHotel(@Parameter(description = "ID del hotel cuyas reservas se desean buscar") @PathVariable("idHotel") Long idHotel) {
		return reservaService.findReservasByHotel(idHotel);
	}
	
	@Operation(summary = "Buscar reservas por nombre de hotel", description = "Busca reservas a partir del nombre del hotel proporcionado en la dirección")
    @GetMapping(value = "/hotel/nombre/{nombreHotel}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reserva> getReservasByNombreHotel(@Parameter(description = "Nombre del hotel cuyas reservas se desean buscar") @PathVariable("nombreHotel") String nombreHotel) {
        return reservaService.findReservasByNombreHotel(nombreHotel);
    }

	@Operation(summary = "Buscar reserva por ID", description = "Busca una reserva a partir del ID proporcionado en la dirección")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Reserva getReservaById(@Parameter(description = "ID de la reserva a buscar") @PathVariable("id") Long id) {
		return reservaService.findById(id).orElse(null);
	}

}