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
    
}