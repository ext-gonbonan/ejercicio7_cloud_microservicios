package com.vuelo.dao;

import com.vuelo.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VueloDao extends JpaRepository<Vuelo, Long> {

	List<Vuelo> findByPlazasDisponiblesGreaterThanEqual(Integer plazas);

}