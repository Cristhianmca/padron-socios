package com.padron.padron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padron.padron.entities.BeneficioSocio;

@Repository
public interface BeneficioSocioRepository extends JpaRepository<BeneficioSocio, Long> {
}