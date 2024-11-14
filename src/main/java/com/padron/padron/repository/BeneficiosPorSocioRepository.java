package com.padron.padron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padron.padron.entities.BeneficioPorSocio;
import com.padron.padron.entities.Socios;

@Repository
public interface BeneficiosPorSocioRepository extends JpaRepository<BeneficioPorSocio, Long> {
    List<BeneficioPorSocio> findBySocio(Socios socio);
}
