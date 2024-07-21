package com.reserva.service;

import com.reserva.model.Reserva;
import java.util.List;

public interface ReservaService {

	Reserva createReserva(Reserva reserva);

	List<Reserva> findReservasByHotel(Long idHotel);

	Reserva findById(Long id);
	
	List<Reserva> findReservasByNombreHotel(String nombreHotel);
	
	Reserva updateReserva(Long id, Reserva updatedReserva);
	
	boolean deleteReserva(Long id);
	
	boolean existeReservaPorVuelo(Long idVuelo);

}