package com.padron.padron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padron.padron.entities.Socios;


@Repository
public interface SociosRepository extends JpaRepository<Socios, Long> {

    @Query("select s from Socios s where s.dni = :dni and s.clave = :clave")
    Socios leeLogin(String dni, String clave);
}
