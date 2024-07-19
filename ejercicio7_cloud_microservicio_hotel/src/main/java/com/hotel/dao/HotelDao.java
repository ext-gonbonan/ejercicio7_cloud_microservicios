package com.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Long> {

}