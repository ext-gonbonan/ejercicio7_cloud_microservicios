package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hotel.dao.HotelDao;
import com.hotel.model.Hotel;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDao hotelDao;

	@Override
	public List<Hotel> findAllAvailable() {
		return hotelDao.findByDisponible(true);
	}

	@Override
	public Hotel findByName(String name) {
		return hotelDao.findByNombre(name).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado con nombre: " + name)
				);
	}
	
	@Override
	public Long findIdByName(String nombre) {
	    Optional<Hotel> optionalHotel = hotelDao.findByNombre(nombre);
	    if (optionalHotel.isPresent()) {
	        return optionalHotel.get().getId();
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado con nombre: " + nombre);
	    }
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		// Verifica si ya existe un hotel con el mismo nombre
        Optional<Hotel> existingHotel = hotelDao.findByNombre(hotel.getNombre());
        
        if (existingHotel.isPresent()) {
            // Si ya existe un hotel con ese nombre, devuelve una excepction
        	throw new ResponseStatusException(HttpStatus.CONFLICT, "Hotel ya existe con nombre: " + hotel.getNombre());
        }
                
        // Si no existe, guarda el nuevo hotel y devuelve un Optional con el hotel guardado
        return hotelDao.save(hotel);
	}

	@Override
	public Hotel findById(Long id) {
		   return (hotelDao.findById(id).orElseThrow(
				   () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado con ID: " + id))
				   );
	}

	@Override
    public Hotel updateHotel(Long id, Hotel updatedHotel) {
        Optional<Hotel> existingHotel = hotelDao.findById(id);
        if (existingHotel.isPresent()) {
            Hotel hotel = existingHotel.get();
            hotel.setNombre(updatedHotel.getNombre());
            hotel.setCategoria(updatedHotel.getCategoria());
            hotel.setPrecio(updatedHotel.getPrecio());
            hotel.setDisponible(updatedHotel.getDisponible());
            return hotelDao.save(hotel);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado con ID: " + id);
        }
    }

    @Override
    public boolean deleteHotel(Long id) {
        if (hotelDao.existsById(id)) {
            hotelDao.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel no encontrado con ID: " + id);
        }
    }

}