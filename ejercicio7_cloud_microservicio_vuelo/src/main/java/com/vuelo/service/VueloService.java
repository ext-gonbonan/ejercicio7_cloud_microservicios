package com.vuelo.service;

import com.vuelo.model.Vuelo;
import java.util.List;

public interface VueloService {

	List<Vuelo> findAllAvailable(Integer plazas);

	Vuelo updateVueloPlazas(Long idVuelo, Integer plazasReservadas);

	Vuelo saveVuelo(Vuelo vuelo);

	Vuelo findById(Long id);
	
	Vuelo updateVuelo(Long id, Vuelo updatedVuelo);
	
	boolean deleteVuelo(Long id);

}