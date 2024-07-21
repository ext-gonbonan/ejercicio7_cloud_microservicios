package com.hotel.service;

import java.util.List;
import com.hotel.model.Hotel;

public interface HotelService {
	
	List<Hotel> findAllAvailable();

	Hotel findByName(String name);

	Hotel saveHotel(Hotel hotel);
	
	Hotel findById(Long id);
	
	Hotel updateHotel(Long id, Hotel updatedHotel);
	
	boolean deleteHotel(Long id);
	
}