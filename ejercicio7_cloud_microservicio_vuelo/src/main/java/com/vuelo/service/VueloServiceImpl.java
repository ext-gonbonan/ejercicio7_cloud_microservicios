package com.vuelo.service;

import com.vuelo.dao.VueloDao;
import com.vuelo.model.Vuelo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloDao vueloDao;

    @Override
    public List<Vuelo> findAllAvailable(Integer plazas) {
        return vueloDao.findByPlazasDisponiblesGreaterThanEqual(plazas);
    }

    @Override
    public Optional<Vuelo> updateVueloPlazas(Long idVuelo, Integer plazasReservadas) {
        Optional<Vuelo> optionalVuelo = vueloDao.findById(idVuelo);
        if (optionalVuelo.isPresent()) {
            Vuelo vuelo = optionalVuelo.get();
            vuelo.setPlazasDisponibles(vuelo.getPlazasDisponibles() - plazasReservadas);
            vueloDao.save(vuelo);
            return Optional.of(vuelo);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Vuelo saveVuelo(Vuelo vuelo) {
        return vueloDao.save(vuelo);
    }

    @Override
    public Optional<Vuelo> findById(Long id) {
        return vueloDao.findById(id);
    }
    
    @Override
    public Optional<Vuelo> updateVuelo(Long id, Vuelo updatedVuelo) {
        Optional<Vuelo> existingVuelo = vueloDao.findById(id);
        if (existingVuelo.isPresent()) {
            Vuelo vuelo = existingVuelo.get();
            vuelo.setCompania(updatedVuelo.getCompania());
            vuelo.setFechaVuelo(updatedVuelo.getFechaVuelo());
            vuelo.setPrecio(updatedVuelo.getPrecio());
            vuelo.setPlazasDisponibles(updatedVuelo.getPlazasDisponibles());
            return Optional.of(vueloDao.save(vuelo));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteVuelo(Long id) {
        if (vueloDao.existsById(id)) {
            vueloDao.deleteById(id);
            return true;
        }
        return false;
    }
    
}