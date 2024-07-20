package com.reserva.service;

import com.reserva.dao.ReservaDao;
import com.reserva.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaDao reservaDao;

	@Autowired
	private RestTemplate restTemplate;

	private static final String VUELO_SERVICE_URL = "http://vuelo-service/vuelos";
	private static final String HOTEL_SERVICE_URL = "http://hotel-service/hoteles";

	@Override
	public Reserva createReserva(Reserva reserva) {
		// Actualizar plazas de vuelo
		restTemplate.put(VUELO_SERVICE_URL + "/" + reserva.getIdVuelo() + "?plazasReservadas=" + 1, null);
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

	@Override
	public List<Reserva> findReservasByNombreHotel(String nombreHotel) {
		// Obtener idHotel del microservicio de hotel
		Long idHotel = restTemplate.getForObject(HOTEL_SERVICE_URL + "/" + nombreHotel, Long.class);
		return reservaDao.findByIdHotel(idHotel);
	}

}