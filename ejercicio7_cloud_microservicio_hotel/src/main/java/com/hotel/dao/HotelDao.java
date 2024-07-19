package com.hotel.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Long> {

	List<Hotel> findByDisponible(Boolean disponible);

	Optional<Hotel> findByNombre(String nombre);

}