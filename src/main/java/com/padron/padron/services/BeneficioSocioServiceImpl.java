package com.padron.padron.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padron.padron.entities.BeneficioSocio;
import com.padron.padron.repository.BeneficioSocioRepository;
import java.util.Optional;


@Service
public class BeneficioSocioServiceImpl implements BeneficioSocioService {

    @Autowired
    private BeneficioSocioRepository beneficioSocioRepository;

    @Override
    public List<BeneficioSocio> obtenerTodosLosBeneficios() {
        return beneficioSocioRepository.findAll();
    }

    // Implementación del método
    @Override
    public BeneficioSocio obtenerBeneficioPorId(Long id) {
        Optional<BeneficioSocio> beneficio = beneficioSocioRepository.findById(id);
        if (beneficio.isPresent()) {
            return beneficio.get();
        } else {
            throw new RuntimeException("Beneficio no encontrado con ID: " + id);
        }
    }
}