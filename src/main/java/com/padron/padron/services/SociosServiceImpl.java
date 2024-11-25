package com.padron.padron.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padron.padron.entities.Socios;

import com.padron.padron.repository.SociosRepository;

@Service

public class SociosServiceImpl implements SociosService {

    @Autowired
    SociosRepository repository;

    @Override
    public List<Socios> listarSocios(){
        return repository.findAll();
    }

    @Override
    public Socios guardarSocios(Socios socio){
        return repository.save(socio);
    }

    @Override
    public Socios leeIdSocios(Long id){
        return repository.findById(id).orElse(null);
    }
    @Override
    public void eliminarSocio(Long id){
        repository.deleteById(id);
    }
    @Override
    public Socios leeLogin(String dni, String clave) {
        return repository.leeLogin(dni, clave);
    }


}
