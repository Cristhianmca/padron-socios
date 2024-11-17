package com.padron.padron.services;

import java.util.List;

import com.padron.padron.entities.BeneficioSocio;

public interface BeneficioSocioService {
List<BeneficioSocio> obtenerTodosLosBeneficios();
BeneficioSocio obtenerBeneficioPorId(Long id);
BeneficioSocio guardarBeneficio(BeneficioSocio beneficio);
}
