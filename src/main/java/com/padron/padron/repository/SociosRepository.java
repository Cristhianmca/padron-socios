package com.padron.padron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padron.padron.entities.Socios;

@Repository
public interface SociosRepository extends JpaRepository<Socios, Long> {

}
