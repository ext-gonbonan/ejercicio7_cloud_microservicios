package com.reserva.service;

import com.reserva.model.Reserva;
import java.util.List;
import java.util.Optional;

public interface ReservaService {

	Reserva createReserva(Reserva reserva);

	List<Reserva> findReservasByHotel(Long idHotel);

	Optional<Reserva> findById(Long id);

}