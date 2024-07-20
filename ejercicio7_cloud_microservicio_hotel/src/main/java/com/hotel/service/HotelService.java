package com.hotel.service;

import java.util.List;
import java.util.Optional;

import com.hotel.model.Hotel;

public interface HotelService {
	
	List<Hotel> findAllAvailable();

	Optional<Hotel> findByName(String name);

	Optional<Hotel> saveHotel(Hotel hotel);
	
	Optional<Hotel> findById(Long id);
	
}