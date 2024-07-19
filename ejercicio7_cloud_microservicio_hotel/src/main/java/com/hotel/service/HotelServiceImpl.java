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
	public Hotel saveHotel(Hotel hotel) {
		return hotelDao.save(hotel);
	}

	@Override
	public Optional<Hotel> findById(Long id) {
		return hotelDao.findById(id);
	}

}