package com.padron.padron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padron.padron.entities.BeneficioPorSocio;

@Repository
public interface BeneficiosPorSocioRepository extends JpaRepository<BeneficioPorSocio, Long> {
    List<BeneficioPorSocio> findBySocioIdsocio(Long idsocio);
}