package com.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Optional<Hotel> findByName(String name) {
		return hotelDao.findByNombre(name);
	}

	@Override
	public Optional<Hotel> saveHotel(Hotel hotel) {
		// Verifica si ya existe un hotel con el mismo nombre
        Optional<Hotel> existingHotel = hotelDao.findByNombre(hotel.getNombre());
        
        if (existingHotel.isPresent()) {
            // Si ya existe un hotel con ese nombre, devuelve un Optional vacío
            return Optional.empty();
        }
                
        // Si no existe, guarda el nuevo hotel y devuelve un Optional con el hotel guardado
        return Optional.of(hotelDao.save(hotel));
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		return hotelDao.findById(id);
	}

}