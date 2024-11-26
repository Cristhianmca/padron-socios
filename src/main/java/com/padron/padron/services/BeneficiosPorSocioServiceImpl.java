package com.padron.padron.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padron.padron.entities.BeneficioPorSocio;
import com.padron.padron.repository.BeneficioPorSocioRepository;


@Service
public class BeneficiosPorSocioServiceImpl implements BeneficiosPorSocioService {

    @Autowired
    private BeneficioPorSocioRepository beneficiosPorSocioRepository;

    @Override
    public BeneficioPorSocio guardarBeneficioPorSocio(BeneficioPorSocio beneficiosPorSocio) {
        return beneficiosPorSocioRepository.save(beneficiosPorSocio);
    }

    @Override
    public List<BeneficioPorSocio> obtenerBeneficiosPorSocio(Long socioId) {
        return beneficiosPorSocioRepository.findBySocioIdsocio(socioId);
    }

    @Override
    public List<BeneficioPorSocio> obtenerTodosLosBeneficios() {
        return beneficiosPorSocioRepository.findAll();
    }
    @Override
    public BeneficioPorSocio obtenerBeneficioPorId(Long id) {
        return beneficiosPorSocioRepository.findById(id).orElse(null);
    }
}