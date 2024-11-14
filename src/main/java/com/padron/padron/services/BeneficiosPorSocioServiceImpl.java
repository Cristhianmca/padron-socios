package com.padron.padron.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padron.padron.entities.BeneficioPorSocio;
import com.padron.padron.repository.BeneficiosPorSocioRepository;

@Service
public class BeneficiosPorSocioServiceImpl implements BeneficiosPorSocioService {

    @Autowired
    private BeneficiosPorSocioRepository beneficiosPorSocioRepository;

    @Override
    public BeneficioPorSocio guardarBeneficioPorSocio(BeneficioPorSocio beneficiosPorSocio) {
        // Aquí podrías implementar alguna lógica adicional antes de guardar, por ejemplo, validar si el beneficio ya está asignado.
        return beneficiosPorSocioRepository.save(beneficiosPorSocio);
    }

    @Override
    public List<BeneficioPorSocio> obtenerBeneficiosPorSocio(Long socioId) {
        // Recupera todos los beneficios asignados a un socio específico
        return beneficiosPorSocioRepository.findBySocioId(socioId);
    }

    @Override
    public List<BeneficioPorSocio> obtenerTodosLosBeneficios() {
        // Recupera todos los registros de beneficios asignados
        return beneficiosPorSocioRepository.findAll();
    }
}