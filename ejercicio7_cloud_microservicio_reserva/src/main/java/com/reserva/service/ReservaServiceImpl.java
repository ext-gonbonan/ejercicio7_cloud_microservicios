package com.reserva.service;

import com.reserva.dao.ReservaDao;
import com.reserva.exception.ReservaCreationException;
import com.reserva.exception.VueloNotFoundException;
import com.reserva.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaDao reservaDao;

	@Autowired
	private RestTemplate restTemplate;

	private static final String VUELO_SERVICE_URL = "http://07-servicio-vuelo/vuelos";
	private static final String HOTEL_SERVICE_URL = "http://07-servicio-hotel/hoteles";

	/*
	@Override
	public Reserva createReserva(Reserva reserva) {
		try {
			// Actualizar plazas de vuelo
			restTemplate.put(VUELO_SERVICE_URL + "/" + reserva.getIdVuelo() + "/" + reserva.getTotalPersonas(), null);
		        
			// Si no se lanzó ninguna excepción, la actualización fue exitosa
			return reservaDao.save(reserva);
			
		} catch (RestClientException e) {
			// La actualización del vuelo falló
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pudo actualizar las plazas del vuelo: " + e.getMessage());
		}
	}
	*/
	
	@Override
    public Reserva createReserva(Reserva reserva) {
        try {
            // Intenta actualizar plazas de vuelo
            restTemplate.put(VUELO_SERVICE_URL + "/" + reserva.getIdVuelo() + "/" + reserva.getTotalPersonas(), null);
            return reservaDao.save(reserva);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new VueloNotFoundException("Vuelo no encontrado con ID: " + reserva.getIdVuelo());
            }
            throw new ReservaCreationException("Error al crear la reserva");
        } catch (Exception e) {
            throw new ReservaCreationException("Error inesperado al crear la reserva");
        }
    }

	@Override
	public List<Reserva> findReservasByIdHotel(Long idHotel) {
		return reservaDao.findByIdHotel(idHotel);
	}

	@Override
	public Reserva findById(Long id) {
		 return (reservaDao.findById(id).orElseThrow(
				 () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada con ID: " + id))
				 );
	}

	@Override
	public List<Reserva> findReservasByNombreHotel(String nombreHotel) {
		// Obtener idHotel del microservicio de hotel
		Long idHotel = restTemplate.getForObject(HOTEL_SERVICE_URL + "/idPorNombre/" + nombreHotel, Long.class);
		return reservaDao.findByIdHotel(idHotel);
	}
	
	@Override
    public Reserva updateReserva(Long id, Reserva updatedReserva) {
        Optional<Reserva> existingReserva = reservaDao.findById(id);
        if (existingReserva.isPresent()) {
            Reserva reserva = existingReserva.get();
            reserva.setNombreCliente(updatedReserva.getNombreCliente());
            reserva.setDni(updatedReserva.getDni());
            reserva.setIdHotel(updatedReserva.getIdHotel());
            reserva.setIdVuelo(updatedReserva.getIdVuelo());
            return reservaDao.save(reserva);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada con ID: " + id);
        }
    }

	// No es recomendado usar @Transactional en los microservicios por lo que aplico otra lógica
	public boolean deleteReserva(Long id) {
	    Optional<Reserva> existingReserva = reservaDao.findById(id);
	    if (existingReserva.isPresent()) {
	        Reserva reserva = existingReserva.get();
	        try {
	            // Paso 1: Eliminanos la reserva
	            reservaDao.deleteById(id);
	            
	            // Paso 2: Liberamos las plazas
	            try {
	                restTemplate.put(VUELO_SERVICE_URL + "/" + reserva.getIdVuelo() + "/" + (-reserva.getTotalPersonas()), null);
	            } catch (Exception e) {
	                // Si falla la liberación de plazas, revertimos la eliminación de la reserva
	                reservaDao.save(reserva);
	                // y enviamos excepcion con el error
	                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudieron liberar las plazas del vuelo. La reserva no se eliminó.");
	            }
	            return true;
	            
	        } catch (Exception e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la reserva: " + e.getMessage());
	        }
	        
	    } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada con ID: " + id);
	    }
	}

	@Override
	public boolean existeReservaPorVuelo(Long idVuelo) {
		List<Reserva> reservas = reservaDao.findByIdVuelo(idVuelo);
	    return !reservas.isEmpty();
	}

}