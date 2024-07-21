package com.vuelo.service;

import com.vuelo.model.Vuelo;
import java.util.List;
import java.util.Optional;

public interface VueloService {

	List<Vuelo> findAllAvailable(Integer plazas);

	Optional<Vuelo> updateVueloPlazas(Long idVuelo, Integer plazasReservadas);

	Vuelo saveVuelo(Vuelo vuelo);

	Vuelo findById(Long id);
	
	Optional<Vuelo> updateVuelo(Long id, Vuelo updatedVuelo);
	
	boolean deleteVuelo(Long id);

}