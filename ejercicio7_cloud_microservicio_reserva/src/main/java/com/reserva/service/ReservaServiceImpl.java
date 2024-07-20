package com.reserva.service;

import com.reserva.dao.ReservaDao;
import com.reserva.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaDao reservaDao;

	@Override
	public Reserva createReserva(Reserva reserva) {
		// agregar lógica adicional para interactuar con otros  microservicios
		return reservaDao.save(reserva);
	}

	@Override
	public List<Reserva> findReservasByHotel(Long idHotel) {
		return reservaDao.findByIdHotel(idHotel);
	}

	@Override
	public Optional<Reserva> findById(Long id) {
		return reservaDao.findById(id);
	}

}