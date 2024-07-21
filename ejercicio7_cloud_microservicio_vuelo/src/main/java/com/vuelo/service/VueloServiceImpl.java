package com.vuelo.service;

import com.vuelo.dao.VueloDao;
import com.vuelo.model.Vuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloDao vueloDao;
    
    @Autowired
    private RestTemplate restTemplate;

    private static final String RESERVA_SERVICE_URL = "http://reserva-service/reservas/vuelo";

    @Override
    public List<Vuelo> findAllAvailable(Integer plazas) {
        return vueloDao.findByPlazasDisponiblesGreaterThanEqual(plazas);
    }

    public Vuelo updateVueloPlazas(Long idVuelo, Integer plazasReservadas) {
        Optional<Vuelo> optionalVuelo = vueloDao.findById(idVuelo);
        if (optionalVuelo.isPresent()) {
            Vuelo vuelo = optionalVuelo.get();
            if (vuelo.getPlazasDisponibles() >= plazasReservadas) {
                vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - plazasReservadas);
                vueloDao.save(vuelo);
                return vuelo;
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "No hay suficientes plazas disponibles.");
            }
        } else {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vuelo no encontrado con ID: " + idVuelo);
        }
    }

    @Override
    public Vuelo saveVuelo(Vuelo vuelo) {
        return vueloDao.save(vuelo);
    }

    @Override
    public Vuelo findById(Long id) {
        return (vueloDao.findById(id).orElseThrow(
        		()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vuelo no encontrado con ID: " + id))
        		);
    }
    
    @Override
    public Vuelo updateVuelo(Long id, Vuelo updatedVuelo) {
        Optional<Vuelo> existingVuelo = vueloDao.findById(id);
        if (existingVuelo.isPresent()) {
            Vuelo vuelo = existingVuelo.get();
            vuelo.setCompania(updatedVuelo.getCompania());
            vuelo.setFechaVuelo(updatedVuelo.getFechaVuelo());
            vuelo.setPrecio(updatedVuelo.getPrecio());
            vuelo.setPlazasDisponibles(updatedVuelo.getPlazasDisponibles());
            return vueloDao.save(vuelo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vuelo no encontrado con ID: " + id);
        }
    }

    @Override
    public boolean deleteVuelo(Long id) {
        if (vueloDao.existsById(id)) {
            // Verificar si hay reservas activas para el vuelo
            Boolean reservasActivas = restTemplate.getForObject(RESERVA_SERVICE_URL + "/" + id, Boolean.class);
            if (reservasActivas != null && reservasActivas) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "No se puede eliminar el vuelo porque tiene reservas activas.");
            }
            // si se puede eliminar el vuelo al no tener reservas activas
            vueloDao.deleteById(id);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vuelo no encontrado con ID: " + id);
        }
    }
    
}