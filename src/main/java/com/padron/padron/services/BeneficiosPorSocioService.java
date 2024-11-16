package com.padron.padron.services;

import com.padron.padron.entities.BeneficioPorSocio;

import java.util.List;

public interface BeneficiosPorSocioService {
    BeneficioPorSocio guardarBeneficioPorSocio(BeneficioPorSocio beneficiosPorSocio);
    List<BeneficioPorSocio> obtenerBeneficiosPorSocio(Long socioId);
    List<BeneficioPorSocio> obtenerTodosLosBeneficios();
    
}