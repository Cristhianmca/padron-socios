package com.padron.padron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.padron.padron.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("select u from Usuario u where u.dni = :dni and u.clave = :clave")
    Usuario leeLogin(String dni, String clave);
}
